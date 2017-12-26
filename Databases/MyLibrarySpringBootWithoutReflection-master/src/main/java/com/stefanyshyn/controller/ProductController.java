package com.stefanyshyn.controller;

import com.stefanyshyn.DTO.ProductDTO;
import com.stefanyshyn.domain.Product;
import com.stefanyshyn.domain.Shop;
import com.stefanyshyn.exceptions.*;
import com.stefanyshyn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping(value = "/api/product/shop/{IDShop}")
    public ResponseEntity<List<ProductDTO>> getProductsByShopID(@PathVariable Long IDShop) throws NoSuchShopException, NoSuchProductException, NoSuchCustomerException {
        List<Product> productList = productService.getProductByShopId(IDShop);

        Link link = linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel();

        List<ProductDTO> productsDTO = new ArrayList<>();
        for (Product entity : productList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            ProductDTO dto = new ProductDTO(entity, selfLink);
            productsDTO.add(dto);
        }

        return new ResponseEntity<>(productsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/product/{IDProduct}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long IDProduct) throws NoSuchProductException, NoSuchCustomerException {
        Product product = productService.getProduct(IDProduct);
        Link link = linkTo(methodOn(ProductController.class).getProduct(IDProduct)).withSelfRel();

        ProductDTO productDTO = new ProductDTO(product, link);

       return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/product")
    public ResponseEntity<List<ProductDTO>> getAllProducts() throws NoSuchProductException, NoSuchCustomerException {
        List<Product> productList = productService.getAllProducts();
        Link link = linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel();

        List<ProductDTO> productDTO = new ArrayList<>();
        for (Product entity : productList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            ProductDTO dto = new ProductDTO(entity, selfLink);
            productDTO.add(dto);
        }

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/product/customer/{IDCustomer}")
    public ResponseEntity<List<ProductDTO>> getProductsByCustomerID(@PathVariable Long IDCustomer) throws NoSuchCustomerException, NoSuchProductException {
        Set<Product> productList = productService.getProductsByCustomerId(IDCustomer);
        Link link = linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel();

        List<ProductDTO> productsDTO = new ArrayList<>();
        for (Product entity : productList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            ProductDTO dto = new ProductDTO(entity, selfLink);
            productsDTO.add(dto);
        }

        return new ResponseEntity<>(productsDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/product/shop/{IDShop}")
    public  ResponseEntity<ProductDTO> addProduct(@RequestBody Product newProduct, @PathVariable Long IDShop)
            throws NoSuchShopException, NoSuchProductException, NoSuchCustomerException {
        productService.createProduct(newProduct, IDShop);
        Link link = linkTo(methodOn(ProductController.class).getProduct(newProduct.getId())).withSelfRel();

        ProductDTO productDTO = new ProductDTO(newProduct, link);

        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/product/{IDProduct}/Shop/{IDShop}")
    public  ResponseEntity<ProductDTO> updateProduct(@RequestBody Product uProduct,
                                                   @PathVariable Long IDProduct, @PathVariable Long IDShop)
            throws NoSuchShopException, NoSuchProductException, NoSuchCustomerException {
        productService.updateProduct(uProduct, IDProduct, IDShop);
        Product product =productService.getProduct(IDProduct);
        Link link = linkTo(methodOn(ProductController.class).getProduct(IDProduct)).withSelfRel();

        ProductDTO productDTO = new ProductDTO(product, link);

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/product/{IDProduct}")
    public  ResponseEntity deleteProduct(@PathVariable Long IDProduct) throws NoSuchProductException, ExistsCustomersForProductException {
        productService.deleteProduct(IDProduct);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/api/product/{IDProduct}/customer/{IDCustomer}")
    public  ResponseEntity<ProductDTO> addProductForProduct(@PathVariable Long IDProduct, @PathVariable Long IDCustomer)
            throws NoSuchProductException, NoSuchCustomerException, AlreadyExistsCustomerInProductException, CustomerAbsentException {
        productService.addCustomerForProduct(IDProduct,IDCustomer);
        Product product = productService.getProduct(IDProduct);
        Link link = linkTo(methodOn(ProductController.class).getProduct(IDProduct)).withSelfRel();

        ProductDTO productDTO = new ProductDTO(product, link);

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/product/{IDProduct}/customer/{IDCustomer}")
    public  ResponseEntity<ProductDTO> removeProductForProduct(@PathVariable Long IDProduct, @PathVariable Long IDCustomer)
            throws NoSuchProductException, NoSuchCustomerException, ProductHasNotCustomerException {
        productService.removeCustomerForProduct(IDProduct,IDCustomer);
        Product product = productService.getProduct(IDProduct);
        Link link = linkTo(methodOn(ProductController.class).getProduct(IDProduct)).withSelfRel();

        ProductDTO productDTO = new ProductDTO(product, link);

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

}
