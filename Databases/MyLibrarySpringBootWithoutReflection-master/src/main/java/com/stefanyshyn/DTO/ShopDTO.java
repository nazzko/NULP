package com.stefanyshyn.DTO;

import com.stefanyshyn.controller.ProductController;
import com.stefanyshyn.controller.ShopController;
import com.stefanyshyn.domain.Shop;
import com.stefanyshyn.exceptions.NoSuchCustomerException;
import com.stefanyshyn.exceptions.NoSuchShopException;
import com.stefanyshyn.exceptions.NoSuchProductException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ShopDTO extends ResourceSupport {
    Shop Shop;
    public ShopDTO(Shop Shop, Link selfLink) throws NoSuchProductException, NoSuchCustomerException, NoSuchShopException {
        this.Shop=Shop;
        add(selfLink);
        add(linkTo(methodOn(ProductController.class).getProductsByShopID(Shop.getId())).withRel("persons"));
    }

    public Long getShopId() { return Shop.getId(); }

    public String getShop() {
        return Shop.getShop();
    }
}