package com.stefanyshyn.service;

import com.stefanyshyn.Repository.CustomerRepository;
import com.stefanyshyn.Repository.ShopRepository;
import com.stefanyshyn.Repository.ProductRepository;
import com.stefanyshyn.domain.Customer;
import com.stefanyshyn.domain.Shop;
import com.stefanyshyn.domain.Product;
import com.stefanyshyn.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ShopRepository ShopRepository;

    @Autowired
    CustomerRepository customerRepository;

    public List<Product> getProductByShopId(Long IDShop) throws NoSuchShopException {
//        Shop Shop = ShopRepository.findOne(IDShop);//1.5.9
        Shop Shop = ShopRepository.findById(IDShop).get();//2.0.0.M7
        if (Shop == null) throw new NoSuchShopException();
        return Shop.getProducts();
    }

    public Product getProduct(Long IDProduct) throws NoSuchProductException {
//        Product product = productRepository.findOne(IDProduct);//1.5.9
        Product product = productRepository.findById(IDProduct).get();//2.0.0.M7
        if (product == null) throw new NoSuchProductException();
        return product;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Set<Product> getProductsByCustomerId(Long IDCustomer) throws NoSuchCustomerException {
//        Customer customer = customerRepository.findOne(IDCustomer);//1.5.9
        Customer customer = customerRepository.findById(IDCustomer).get();//2.0.0.M7
        if (customer == null) throw new NoSuchCustomerException();
        return customer.getProducts();
    }

    @Transactional
    public void createProduct(Product product, Long IDShop) throws NoSuchShopException {
        if (IDShop > 0) {
//            Shop Shop = ShopRepository.findOne(IDShop);//1.5.9
            Shop Shop = ShopRepository.findById(IDShop).get();//2.0.0.M7
            if (Shop == null) throw new NoSuchShopException();
            product.setShop(Shop);
        }
        productRepository.save(product);
    }

    @Transactional
    public void updateProduct(Product uProduct, Long IDProduct, Long IDShop) throws NoSuchShopException, NoSuchProductException {
//        Shop Shop = ShopRepository.findOne(IDShop);//1.5.9
        Shop Shop = ShopRepository.findById(IDShop).get();//2.0.0.M7
        if (IDShop > 0) {
            if (Shop == null) throw new NoSuchShopException();
        }
//        Product product = productRepository.findOne(IDProduct);//1.5.9
        Product product = productRepository.findById(IDProduct).get();//2.0.0.M7
        if (product == null) throw new NoSuchProductException();
        //update
        product.setProductName(uProduct.getProductName());
        product.setAmount(uProduct.getAmount());
        if (IDShop > 0) product.setShop(Shop);
        else product.setShop(null);
        productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(Long IDProduct) throws NoSuchProductException, ExistsCustomersForProductException {
//        Product product = productRepository.findOne(IDProduct);//1.5.9
        Product product = productRepository.findById(IDProduct).get();//2.0.0.M7
        if (product == null) throw new NoSuchProductException();
        if (product.getCustomers().size() != 0) throw new ExistsCustomersForProductException();
        productRepository.delete(product);
    }

    @Transactional
    public void addCustomerForProduct(Long IDProduct, Long IDCustomer)
            throws NoSuchProductException, NoSuchCustomerException, AlreadyExistsCustomerInProductException, CustomerAbsentException {
//        Product product = productRepository.findOne(IDProduct);//1.5.9
        Product product = productRepository.findById(IDProduct).get();//2.0.0.M7
        if (product == null) throw new NoSuchProductException();
//        Customer customer = customerRepository.findOne(IDCustomer);//1.5.9
        Customer customer = customerRepository.findById(IDCustomer).get();//2.0.0.M7
        if (customer == null) throw new NoSuchCustomerException();
        if (product.getCustomers().contains(customer) == true) throw new AlreadyExistsCustomerInProductException();
        if (product.getAmount() <= customer.getProducts().size()) throw new CustomerAbsentException();
        product.getCustomers().add(customer);
        productRepository.save(product);
    }

    @Transactional
    public void removeCustomerForProduct(Long IDProduct, Long IDCustomer)
            throws NoSuchProductException, NoSuchCustomerException, ProductHasNotCustomerException {
//        Product product = productRepository.findOne(IDProduct);//1.5.9
        Product product = productRepository.findById(IDProduct).get();//2.0.0.M7
        if (product == null) throw new NoSuchProductException();
//        Customer customer = customerRepository.findOne(IDCustomer);//1.5.9
        Customer customer = customerRepository.findById(IDCustomer).get();//2.0.0.M7
        if (customer == null) throw new NoSuchCustomerException();
        if (product.getCustomers().contains(customer) == false) throw new ProductHasNotCustomerException();
        product.getCustomers().remove(customer);
        productRepository.save(product);
    }
}
