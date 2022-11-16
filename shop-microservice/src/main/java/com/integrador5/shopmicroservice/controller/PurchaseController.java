package com.integrador5.shopmicroservice.controller;

import com.integrador5.shopmicroservice.DTO.PurchasesPerDayDTO;
import com.integrador5.shopmicroservice.model.Product;
import com.integrador5.shopmicroservice.model.Purchase;
import com.integrador5.shopmicroservice.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping(value = "/cartlist/idclient/{id}")
    public void purchase(@RequestBody List<Product> toPurchase,@PathVariable Integer id){
        this.purchaseService.purchase(toPurchase,id);
    }

    @GetMapping(value = "/")
    public List<Purchase> getAllPurchase (){
        return this.purchaseService.getAllPurchases();
    }

    @GetMapping(value = "/purchasePerDayReport")
    public List<PurchasesPerDayDTO> getPurchasesPerDayReport(){
        return this.purchaseService.getPurchasesPerDayReport();
    }

}
