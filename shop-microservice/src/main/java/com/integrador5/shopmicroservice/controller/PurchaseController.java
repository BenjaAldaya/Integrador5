package com.integrador5.shopmicroservice.controller;

import com.integrador5.shopmicroservice.DTO.PurchasesPerDayDTO;
import com.integrador5.shopmicroservice.model.Product;
import com.integrador5.shopmicroservice.model.Purchase;
import com.integrador5.shopmicroservice.service.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name="Purchase", description="Purchases related resources")
@RequestMapping(value = "/api/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping(value = "/cartlist/idclient/{id}")
    @Operation(summary="Do a purchase ", description="Add a purchase in our shop")
    public void purchase(@RequestBody List<Product> toPurchase,@PathVariable Integer id){
        this.purchaseService.purchase(toPurchase,id);
    }

    @GetMapping(value = "/")
    @Operation(summary="Get all purchases ", description="List of purchases")
    public List<Purchase> getAllPurchase (){
        return this.purchaseService.getAllPurchases();
    }

    @GetMapping(value = "/purchasePerDayReport")
    @Operation(summary="Get a report with all the purchases per day ", description="List of daily purchases")
    public List<PurchasesPerDayDTO> getPurchasesPerDayReport(){
        return this.purchaseService.getPurchasesPerDayReport();
    }

}
