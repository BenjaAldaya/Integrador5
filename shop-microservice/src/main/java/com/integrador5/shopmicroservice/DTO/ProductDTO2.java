package com.integrador5.shopmicroservice.DTO;

public class ProductDTO2 {
    private Integer id_product;
    private String name;
    private float quantity;

    public ProductDTO2(Integer id_product, String name, float quantity) {
        this.id_product = id_product;
        this.name = name;
        this.quantity = quantity;
    }
}
