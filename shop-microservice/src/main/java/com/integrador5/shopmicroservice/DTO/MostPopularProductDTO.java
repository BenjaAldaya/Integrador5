package com.integrador5.shopmicroservice.DTO;

public class MostPopularProductDTO {
    private Integer idProduct;
    private String nameProduct;
    private int quantity;

    public MostPopularProductDTO(Integer idProduct, String nameProduct, int quantity) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.quantity = quantity;
    }
}
