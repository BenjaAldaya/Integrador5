package com.integrador5.shopmicroservice.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
//TODO: borrarle la notacion JPA??

@Entity
@Setter
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private Integer id_product;

    @Column
    private String name;

    @Column
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    public Product() {
    }

    public Product(Integer id,int quantity) {
        this.id = id;
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", purchase=" + purchase +
                '}';
    }
}