package com.integrador5.productmicroservice.controller;

import com.integrador5.productmicroservice.models.Product;
import com.integrador5.productmicroservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(value = "/")
    public List<Product> getAllProducts(){
        return this.productService.getAllProducts();
    }
}
