package com.integrador5.productmicroservice.controller;

import com.integrador5.productmicroservice.models.Product;
import com.integrador5.productmicroservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/insert")
    public void insertarProducto(@RequestBody Product product){
        return this.productService.insertProduct(product);
    }


    @DeleteMapping(value = "/delete/id/{id}")
    public void deleteProduct(@PathVariable Integer id){
        this.productService.deleteProduct(id);
    }

    @PutMapping(value="/update/id/{id}")
    public void updateProduct(@PathVariable Integer id, @RequestBody Product product){
        this.productService.updateProduct(id,product);
    }
}
