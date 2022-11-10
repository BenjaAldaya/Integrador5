package com.integrador5.shopmicroservice.controller;

import com.integrador5.shopmicroservice.model.Product;
import com.integrador5.shopmicroservice.model.Purchase;
import com.integrador5.shopmicroservice.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(value = "/api/purchase")
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @PostMapping(value = "/cartlist")
    public void purchase(@RequestBody List<Product> toPurchase){
        this.purchaseService.Purchase(toPurchase);
    }

}
