package com.integrador5.shopmicroservice.DTO;

public class PurchasesPerDayDTO {
    private String date;
    private int cantPurchases;
    private float totalPrice;

    public PurchasesPerDayDTO(String date, int cantPurchases, float totalPrice) {
        this.date = date;
        this.cantPurchases = cantPurchases;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "PurchasesPerDayDTO{" +
                "date=" + date +
                ", cantPurchases=" + cantPurchases +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
