package com.stefanyshyn.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Shop")
public class Shop {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDShop", nullable = false)
    private Long id;
    @Column(name = "Shop", nullable = false, length = 25)
    private String Shop;
    @OneToMany(mappedBy = "Shop")
    private List<Product> products;

    Shop(){}
    Shop(String Shop){
        this.Shop=Shop;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long idShop) {
        this.id = idShop;
    }

    public String getShop() {
        return Shop;
    }
    public void setShop(String Shop) {
        this.Shop = Shop;
    }

    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop that = (Shop) o;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (Shop != null ? !Shop.equals(that.Shop) : that.Shop != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (Shop != null ? Shop.hashCode() : 0);
        return result;
    }
}
