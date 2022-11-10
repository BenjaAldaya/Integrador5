package com.integrador5.productmicroservice.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_product;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private float price;

    @Column
    private int stock;

    public Product(){}
    public Product(String nombre, String descripcion, float precio, int stock) {
        this.name = nombre;
        this.description = descripcion;
        this.price = precio;
        this.stock = stock;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    public void setDescription(String descripcion) {
        this.description = descripcion;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Integer getId_product() {
        return id_product;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id_producto=" + id_product +
                ", nombre='" + name + '\'' +
                ", descripcion='" + description + '\'' +
                ", precio=" + price +
                ", stock=" + stock +
                '}';
    }
}
