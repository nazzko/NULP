package com.stefanyshyn.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDProduct", nullable = false)
    private Long IDProduct;
    @Column(name = "ProductName", nullable = false, length = 25)
    private String ProductName;
    @Column(name = "Amount", nullable = true, length = 45)
    private Integer Amount;
    @Column(name = "Price", nullable = true, length = 30)
    private Integer Price;
    @ManyToOne
    @JoinColumn(name = "IDShop", referencedColumnName = "IDShop")
    private Shop Shop;
    @ManyToMany
    @JoinTable(name = "productcustomer",
            joinColumns = @JoinColumn(name = "IDProduct", referencedColumnName = "IDProduct", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "IDCustomer", referencedColumnName = "IDCustomer", nullable = false))
    private Set<Customer> customers;

    Product(){}
    Product(String ProductName, Integer Amount, Integer Price){
        this.ProductName=ProductName;
        this.Amount=Amount;
        this.Price=Price;
    }

    public Long getId() {
        return IDProduct;
    }
    public void setId(Long idProduct) {
        this.IDProduct = idProduct;
    }

    public String getProductName() {
        return ProductName;
    }
    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public Integer getAmount() {
        return Amount;
    }
    public void setAmount(Integer Amount) {
        this.Amount = Amount;
    }

    public Integer getPrice() {
        return Price;
    }
    public void setPrice(Integer Price) {
        this.Price = Price;
    }

    public Shop getShop() {
        return Shop;
    }
    public void setShop(Shop Shop) {
        this.Shop = Shop;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }
    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product that = (Product) o;
        if (IDProduct != null ? !IDProduct.equals(that.IDProduct) : that.IDProduct != null) return false;
        if (ProductName != null ? !ProductName.equals(that.ProductName) : that.ProductName != null) return false;
        if (Amount != null ? !Amount.equals(that.Amount) : that.Amount != null) return false;
        if (Price != null ? !Price.equals(that.Price) : that.Price != null) return false;
   return true;
    }

    @Override
    public int hashCode() {
        int result = IDProduct != null ? IDProduct.hashCode() : 0;
        result = 31 * result + (ProductName != null ? ProductName.hashCode() : 0);
        result = 31 * result + (Amount != null ? Amount.hashCode() : 0);
        result = 31 * result + (Price != null ? Price.hashCode() : 0);
        return result;
    }
}
