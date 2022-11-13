package com.integrador5.shopmicroservice.model;

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
    private Date date;

    @Column
    private float price;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "purchase")
    private List<Product> productList = new java.util.ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "client_ID")
    private Client client;

    public Purchase() {
    }

    public Purchase(Integer id, Date date, float price, List<Product> productList, Client client) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.productList = productList;
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
