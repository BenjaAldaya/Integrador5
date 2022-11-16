package com.integrador5.shopmicroservice.DTO;

public class MostPopularProductDTO {
    private int idProduct;
    private String nameProduct;
    private int quantity;

    public MostPopularProductDTO(int idProduct, String nameProduct, int quantity) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "MostPopularProductDTO{" +
                "idProduct=" + idProduct +
                ", nameProduct='" + nameProduct + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
