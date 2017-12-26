package com.stefanyshyn.DTO;

import com.stefanyshyn.controller.ProductController;
import com.stefanyshyn.domain.Customer;
import com.stefanyshyn.exceptions.NoSuchCustomerException;
import com.stefanyshyn.exceptions.NoSuchProductException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class CustomerDTO extends ResourceSupport {
    Customer customer;
    public CustomerDTO(Customer customer, Link selfLink) throws NoSuchCustomerException, NoSuchProductException {
        this.customer=customer;
        add(selfLink);
        add(linkTo(methodOn(ProductController.class).getProductsByCustomerID(customer.getId())).withRel("products"));
    }

    public Long getCustomerId() {
        return customer.getId();
    }

    public String getCustomerName() {
        return customer.getCustomerName();
    }

    public String getSurname() {
        return customer.getSurname();
    }

    public String getEmail() {
        return customer.getEmail();
    }
}