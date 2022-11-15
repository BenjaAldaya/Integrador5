package com.integrador5.shopmicroservice.DTO;

public class ProductDTO {

    private Integer id_product;

    private String name;

    private String description;

    private float price;

    private int stock;

    public ProductDTO(){}

    public ProductDTO(Integer id_product, String name, String description, float price, int stock) {
        this.id_product = id_product;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public Integer getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id_product=" + id_product +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}