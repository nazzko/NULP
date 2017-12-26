package com.stefanyshyn.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDCustomer", nullable = false)
    private Long IDCustomer;
    @Column(name = "CustomerName", nullable = false, length = 45)
    private String CustomerName;
    @Column(name = "Surname", nullable = false, length = 45)
    private String Surname;
    @Column(name = "Email", nullable = true, length = 50)
    private String Email;
    @ManyToMany(mappedBy = "customers")
    private Set<Product> products;

    Customer(){}
    Customer(String CustomerName, String Surname, String Email){
        this.CustomerName=CustomerName;
        this.Surname=Surname;
        this.Email=Email;
    }

    public Long getId() {
        return IDCustomer;
    }
    public void setId(Long IDCustomer) {
        this.IDCustomer = IDCustomer;
    }

    public String getCustomerName() {
        return CustomerName;
    }
    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getSurname() {
        return Surname;
    }
    public void setSurname(String Surname) {
        this.Surname = Surname;
    }

    public String getEmail() {
        return Email;
    }
    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Set<Product> getProducts() {
        return products;
    }
    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer that = (Customer) o;
        if (IDCustomer != null ? !IDCustomer.equals(that.IDCustomer) : that.IDCustomer != null) return false;
        if (CustomerName != null ? !CustomerName.equals(that.CustomerName) : that.CustomerName != null) return false;
        if (Surname != null ? !Surname.equals(that.Surname) : that.Surname != null) return false;
        if (Email != null ? !Email.equals(that.Email) : that.Email != null) return false;
     return true;
    }

    @Override
    public int hashCode() {
        int result = IDCustomer != null ? IDCustomer.hashCode() : 0;
        result = 31 * result + (CustomerName != null ? CustomerName.hashCode() : 0);
        result = 31 * result + (Surname != null ? Surname.hashCode() : 0);
        result = 31 * result + (Email != null ? Email.hashCode() : 0);
        return result;
    }

}
