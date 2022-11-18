package com.integrador5.shopmicroservice.controller;

import com.integrador5.shopmicroservice.DTO.MostPopularProductDTO;
import com.integrador5.shopmicroservice.DTO.ProductDTO2;
import com.integrador5.shopmicroservice.model.Product;
import com.integrador5.shopmicroservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name="Product", description="Products sold related resources")
@RequestMapping(value = "/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(value = "/")
    @Operation(summary="Get all products that are sold ", description="List of sold products")
    public List<Product> getAllProduct(){
        return this.productService.getAllProduct();
    }

    @GetMapping(value = "/popularproduct")
    @Operation(summary="Get most popular product ", description="Most popular product in the shop")
    public MostPopularProductDTO popularProducts (){
        return this.productService.getPopularProduct();
       // System.out.println("DTO SOLO" + dto);
        //return dto;
    }
}
