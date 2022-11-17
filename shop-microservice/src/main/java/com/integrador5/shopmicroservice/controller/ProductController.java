package com.integrador5.shopmicroservice.controller;

import com.integrador5.shopmicroservice.DTO.MostPopularProductDTO;
import com.integrador5.shopmicroservice.DTO.ProductDTO2;
import com.integrador5.shopmicroservice.model.Product;
import com.integrador5.shopmicroservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public List<Product> getAllProduct(){
        return this.productService.getAllProduct();
    }

    @GetMapping(value = "/popularproduct")
    public MostPopularProductDTO popularProducts (){
        return this.productService.getPopularProduct();
       // System.out.println("DTO SOLO" + dto);
        //return dto;
    }
}
