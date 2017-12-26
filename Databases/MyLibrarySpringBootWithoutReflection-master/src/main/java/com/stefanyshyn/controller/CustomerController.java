package com.stefanyshyn.controller;


import com.stefanyshyn.DTO.CustomerDTO;
import com.stefanyshyn.domain.Customer;
import com.stefanyshyn.exceptions.ExistsProductForCustomerException;
import com.stefanyshyn.exceptions.NoSuchCustomerException;
import com.stefanyshyn.exceptions.NoSuchProductException;
import com.stefanyshyn.service.CustomerService;
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
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping(value = "/api/customer/product/{IDProduct}")
    public ResponseEntity<List<CustomerDTO>> getCustomersByProductID(@PathVariable Long IDProduct) throws NoSuchProductException, NoSuchCustomerException {
        Set<Customer> customerList = customerService.getCustomersByProductId(IDProduct);
        Link link = linkTo(methodOn(CustomerController.class).getAllCustomers()).withSelfRel();

        List<CustomerDTO> customersDTO = new ArrayList<>();
        for (Customer entity : customerList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            CustomerDTO dto = new CustomerDTO(entity, selfLink);
            customersDTO.add(dto);
        }

        return new ResponseEntity<>(customersDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/customer/{IDCustomer}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long IDCustomer) throws NoSuchCustomerException, NoSuchProductException {
        Customer customer = customerService.getCustomer(IDCustomer);
        Link link = linkTo(methodOn(CustomerController.class).getCustomer(IDCustomer)).withSelfRel();

        CustomerDTO customersDTO = new CustomerDTO(customer, link);

        return new ResponseEntity<>(customersDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/customer")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() throws NoSuchCustomerException, NoSuchProductException {
        List<Customer> customerList = customerService.getAllCustomers();
        Link link = linkTo(methodOn(CustomerController.class).getAllCustomers()).withSelfRel();

        List<CustomerDTO> customersDTO = new ArrayList<>();
        for (Customer entity : customerList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            CustomerDTO dto = new CustomerDTO(entity, selfLink);
            customersDTO.add(dto);
        }

        return new ResponseEntity<>(customersDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/customer")
    public ResponseEntity<CustomerDTO> addCustomer(@RequestBody Customer newCustomer) throws NoSuchCustomerException, NoSuchProductException {
        customerService.createCustomer(newCustomer);
        Link link = linkTo(methodOn(CustomerController.class).getCustomer(newCustomer.getId())).withSelfRel();

        CustomerDTO customerDTO = new CustomerDTO(newCustomer, link);

        return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/customer/{IDProduct}")
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody Customer uCustomer, @PathVariable Long IDProduct) throws NoSuchCustomerException, NoSuchProductException {
        customerService.updateCustomer(uCustomer, IDProduct);
        Customer customer = customerService.getCustomer(IDProduct);
        Link link = linkTo(methodOn(CustomerController.class).getCustomer(IDProduct)).withSelfRel();

        CustomerDTO customerDTO = new CustomerDTO(customer, link);

        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/customer/{IDCustomer}")
    public  ResponseEntity deleteCustomer(@PathVariable Long IDCustomer) throws ExistsProductForCustomerException, NoSuchCustomerException {
        customerService.deleteCustomer(IDCustomer);
        return new ResponseEntity(HttpStatus.OK);
    }
}
