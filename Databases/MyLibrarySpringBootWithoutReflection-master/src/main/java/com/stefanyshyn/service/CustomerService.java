package com.stefanyshyn.service;

import com.stefanyshyn.Repository.CustomerRepository;
import com.stefanyshyn.Repository.ProductRepository;
import com.stefanyshyn.domain.Customer;
import com.stefanyshyn.domain.Product;
import com.stefanyshyn.exceptions.ExistsProductForCustomerException;
import com.stefanyshyn.exceptions.NoSuchCustomerException;
import com.stefanyshyn.exceptions.NoSuchProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    public Set<Customer> getCustomersByProductId(Long IDProduct) throws NoSuchProductException {
//        Product product = productRepository.findOne(IDProduct);//1.5.9
        Product product = productRepository.findById(IDProduct).get();//2.0.0.M7
        if (product == null) throw new NoSuchProductException();
        return product.getCustomers();
    }

    public Customer getCustomer(Long IDCustomer) throws NoSuchCustomerException {
//        Customer customer = customerRepository.findOne(IDCustomer);//1.5.9
        Customer customer = customerRepository.findById(IDCustomer).get();//2.0.0.M7
        if (customer == null) throw new NoSuchCustomerException();
        return customer;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Transactional
    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Transactional
    public void updateCustomer(Customer uCustomer, Long IDCustomer) throws NoSuchCustomerException {
//        Customer customer = customerRepository.findOne(IDCustomer);//1.5.9
        Customer customer = customerRepository.findById(IDCustomer).get();//2.0.0.M7
        if (customer == null) throw new NoSuchCustomerException();
        //update
        customer.setCustomerName(uCustomer.getCustomerName());
        customer.setSurname(uCustomer.getSurname());
        customer.setEmail(uCustomer.getEmail());
    }

    @Transactional
    public void deleteCustomer(Long IDCustomer) throws NoSuchCustomerException, ExistsProductForCustomerException {
//        Customer customer = customerRepository.findOne(IDCustomer);//1.5.9
        Customer customer = customerRepository.findById(IDCustomer).get();//2.0.0.M7

        if (customer == null) throw new NoSuchCustomerException();
        if (customer.getProducts().size() != 0) throw new ExistsProductForCustomerException();
        customerRepository.delete(customer);
    }
}
