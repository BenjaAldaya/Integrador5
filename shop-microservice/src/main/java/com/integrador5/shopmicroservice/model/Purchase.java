package com.integrador5.shopmicroservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String date;

    @Column
    private float price;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "purchase")
    @JsonIgnore
    private List<Product> productList = new java.util.ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "client_ID")
    private Client client;

    public Purchase() {
    }

    public Purchase(String date, List<Product> productList,float price ,Client client) {
        this.date = date;
        this.productList = productList;
        this.price = price;
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
