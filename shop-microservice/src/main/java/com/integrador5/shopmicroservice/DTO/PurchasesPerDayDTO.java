package com.integrador5.shopmicroservice.DTO;

import java.util.Date;

public class PurchasesPerDayDTO {
    private Date date;
    private int cantPurchases;
    private float totalPrice;

    public PurchasesPerDayDTO(Date date, int cantPurchases, float totalPrice) {
        this.date = date;
        this.cantPurchases = cantPurchases;
        this.totalPrice = totalPrice;
    }
}
