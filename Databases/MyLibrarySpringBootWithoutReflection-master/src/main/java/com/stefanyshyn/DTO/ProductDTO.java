package com.stefanyshyn.DTO;

import com.stefanyshyn.controller.CustomerController;
import com.stefanyshyn.domain.Product;
import com.stefanyshyn.exceptions.NoSuchCustomerException;
import com.stefanyshyn.exceptions.NoSuchProductException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ProductDTO extends ResourceSupport {
    Product product;
    public ProductDTO(Product product, Link selfLink) throws NoSuchProductException, NoSuchCustomerException {
        this.product=product;
        add(selfLink);
        add(linkTo(methodOn(CustomerController.class).getCustomersByProductID(product.getId())).withRel("customers"));
    }

    public Long getProductId() {
        return product.getId();
    }

    public String getProductName() {
        return product.getProductName();
    }

    public Integer getAmount() {
        return Integer.valueOf(product.getAmount());
    }

    public String getShop() {
        if(product.getShop()==null) return "";
        return product.getShop().getShop();
    }

    public Integer getPrice() {
        return Integer.valueOf(product.getPrice());
    }

}
