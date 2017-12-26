package com.stefanyshyn.controller;

import com.stefanyshyn.DTO.ShopDTO;
import com.stefanyshyn.domain.Shop;
import com.stefanyshyn.exceptions.ExistsProductsForShopException;
import com.stefanyshyn.exceptions.NoSuchCustomerException;
import com.stefanyshyn.exceptions.NoSuchShopException;
import com.stefanyshyn.exceptions.NoSuchProductException;
import com.stefanyshyn.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class ShopController {
    @Autowired
    ShopService ShopService;

    @GetMapping(value = "/api/shop")
    public ResponseEntity<List<ShopDTO>> getAllShops() throws NoSuchProductException, NoSuchCustomerException, NoSuchShopException {
        List<Shop> ShopList = ShopService.getAllShop();
        Link link = linkTo(methodOn(ShopController.class).getAllShops()).withSelfRel();

        List<ShopDTO> ShopsDTO = new ArrayList<>();
        for (Shop entity : ShopList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            ShopDTO dto = new ShopDTO(entity, selfLink);
            ShopsDTO.add(dto);
        }

        return new ResponseEntity<>(ShopsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/shop/{IDShop}")
    public ResponseEntity<ShopDTO> getShop(@PathVariable Long IDShop) throws NoSuchShopException, NoSuchProductException, NoSuchCustomerException {
        Shop Shop = ShopService.getShop(IDShop);
        Link link = linkTo(methodOn(ShopController.class).getShop(IDShop)).withSelfRel();

        ShopDTO ShopDTO = new ShopDTO(Shop, link);

        return new ResponseEntity<>(ShopDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/shop/{IDShop}")
    public  ResponseEntity<ShopDTO> addShop(@RequestBody Shop newShop) throws NoSuchShopException, NoSuchProductException, NoSuchCustomerException {
        ShopService.createShop(newShop);
        Link link = linkTo(methodOn(ShopController.class).getShop(newShop.getId())).withSelfRel();

        ShopDTO ShopDTO = new ShopDTO(newShop, link);

        return new ResponseEntity<>(ShopDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/shop/{IDShop}")
    public  ResponseEntity<ShopDTO> updateShop(@RequestBody Shop uShop, @PathVariable Long IDShop) throws NoSuchShopException, NoSuchProductException, NoSuchCustomerException {
        ShopService.updateShop(uShop, IDShop);
        Shop Shop = ShopService.getShop(IDShop);
        Link link = linkTo(methodOn(ShopController.class).getShop(IDShop)).withSelfRel();

        ShopDTO ShopDTO = new ShopDTO(Shop, link);

        return new ResponseEntity<>(ShopDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/shop/{IDShop}")
    public  ResponseEntity deleteShop(@PathVariable Long IDShop) throws NoSuchShopException, ExistsProductsForShopException {
        ShopService.deleteShop(IDShop);
        return new ResponseEntity(HttpStatus.OK);
    }

}
