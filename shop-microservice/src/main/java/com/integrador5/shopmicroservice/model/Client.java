package com.integrador5.shopmicroservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_client;

    @Column
    private String name;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Purchase> purchaseslist;

    public Client() {
    }

    public Client(String name) {
        this.purchaseslist = new ArrayList<>();
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id_client=" + id_client +
                ", name='" + name + '\'' +
                ", purchaseslist=" + purchaseslist +
                '}';
    }

    public float getTotalPricePurchases(){
        float result = 0;
        for(Purchase p :purchaseslist){
            result += p.getPrice();
        }
        return result;
    }

}