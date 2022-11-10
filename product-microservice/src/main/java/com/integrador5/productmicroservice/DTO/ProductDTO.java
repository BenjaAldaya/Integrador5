package com.integrador5.productmicroservice.DTO;

public class ProductDTO {
    Integer product_id;
    int quantity;

    public ProductDTO(Integer id, int quantity){
        this.product_id = id;
        this.quantity = quantity;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
