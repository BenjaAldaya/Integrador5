package com.integrador5.shopmicroservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
//TODO: borrarle la notacion JPA??

@Entity
@Setter
@Getter
public class Product {

    @Id
    private Integer id;

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

    public Integer getId() {
        return id;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

//    public float getPrice() {
//        return price;
//    }
//
//    public void setPrice(float price) {
//        this.price = price;
//    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", purchase=" + purchase +
                '}';
    }
}