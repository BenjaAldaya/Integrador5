package com.integrador5.shopmicroservice.DTO;

public class PurchasesPerDayDTO {
    private String date;
    private long cantPurchases;
    private double totalPrice;

    public PurchasesPerDayDTO(String date, long cantPurchases, double totalPrice) {
        this.date = date;
        this.cantPurchases = cantPurchases;
        this.totalPrice = totalPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getCantPurchases() {
        return cantPurchases;
    }

    public void setCantPurchases(int cantPurchases) {
        this.cantPurchases = cantPurchases;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
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
