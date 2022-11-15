package com.integrador5.clientmicroservice.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_client;

    @Column
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client")
    private List<Purchase> purchaseList = new ArrayList<>();

    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(List<Purchase> purchaseList) {
        this.purchaseList = purchaseList;
    }


    public Client() {
    }

    public Client(String name) {
        this.name = name;
        this.purchaseList = new ArrayList<>();
    }

    public Integer getId_client() {
        return id_client;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPurchase(Purchase p){
        this.purchaseList.add(p);
    }


}