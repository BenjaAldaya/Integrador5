package com.integrador5.shopmicroservice.DTO;


public class MostPopularProductDTO{
    private int idProduct;
    private String nameProduct;
    private long quantity;

    public MostPopularProductDTO(int idProduct, String nameProduct, long quantity) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.quantity = quantity;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
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
