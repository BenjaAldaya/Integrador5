package com.integrador5.shopmicroservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id_client;

    @Column
    String name;

    @OneToMany
    @Column
    List<Purchase> purchaseslist;

    public Client() {
    }

    public Client(Integer id_client, String name, List<Purchase> purchaseslist) {
        this.id_client = id_client;
        this.name = name;
        this.purchaseslist = purchaseslist;
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

    public List<Purchase> getPurchaseslist() {
        return purchaseslist;
    }

    public void setPurchaseslist(List<Purchase> purchaseslist) {
        this.purchaseslist = purchaseslist;
    }
}