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
    Integer id;

    @Column
    Date date;

    @Column
    float precio;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "purchase")
    List<Product> productList = new java.util.ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "client_ID")
    Client client;

    public Purchase() {
    }

    public Purchase(Integer id, Date date, float precio, List<Product> productList, Client client) {
        this.id = id;
        this.date = date;
        this.precio = precio;
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
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
