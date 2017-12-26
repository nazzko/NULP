package com.stefanyshyn.service;

import com.stefanyshyn.Repository.ShopRepository;
import com.stefanyshyn.Repository.ProductRepository;
import com.stefanyshyn.domain.Shop;
import com.stefanyshyn.exceptions.ExistsProductsForShopException;
import com.stefanyshyn.exceptions.NoSuchShopException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ShopService {
    @Autowired
    ShopRepository ShopRepository;
    private boolean ascending;

    @Autowired
    ProductRepository productRepository;

    public List<Shop> getAllShop() {
        return ShopRepository.findAll();
    }

    public Shop getShop(Long IDShop) throws NoSuchShopException {
//        Shop Shop =ShopRepository.findOne(IDShop);//1.5.9
        Shop Shop = ShopRepository.findById(IDShop).get();//2.0.0.M7
        if (Shop == null) throw new NoSuchShopException();
        return Shop;
    }

    @Transactional
    public void createShop(Shop Shop) {
        ShopRepository.save(Shop);
    }

    @Transactional
    public void updateShop(Shop uShop, Long IDShop) throws NoSuchShopException {
//        Shop Shop = ShopRepository.findOne(IDShop);//1.5.9
        Shop Shop = ShopRepository.findById(IDShop).get();//2.0.0.M7

        if (Shop == null) throw new NoSuchShopException();
        Shop.setShop(uShop.getShop());
        ShopRepository.save(Shop);
    }

    @Transactional
    public void deleteShop(Long IDShop) throws NoSuchShopException, ExistsProductsForShopException {
//        Shop Shop = ShopRepository.findOne(IDShop);//1.5.9
        Shop Shop = ShopRepository.findById(IDShop).get();//2.0.0.M7
        if (Shop == null) throw new NoSuchShopException();
        if (Shop.getProducts().size() != 0) throw new ExistsProductsForShopException();
        ShopRepository.delete(Shop);
    }
}
