package com.integrador5.productmicroservice.controller;

import com.integrador5.productmicroservice.DTO.ProductDTO;
import com.integrador5.productmicroservice.models.Product;
import com.integrador5.productmicroservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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
        this.productService.insertProduct(product);
    }


    @DeleteMapping(value = "/delete/id/{id}")
    public void deleteProduct(@PathVariable Integer id){
        this.productService.deleteProduct(id);
    }

    @PutMapping(value="/update/id/{id}")
    public void updateProduct(@PathVariable Integer id, @RequestBody Product product){
        this.productService.updateProduct(id,product);
    }

    @PutMapping(value="/update/id/list")
    public void updateProductByList(@RequestBody List<Product> product){
        this.productService.updateProductByList(product);
    }

    @GetMapping(value="/cart")
    public List<Product> canBuyThem(@RequestBody List<ProductDTO> products){
        return this.productService.findCanBuy(products);
    }

    @GetMapping(value = "/id/{id}")
    public Optional<Product> getById(@PathVariable Integer id){
        return this.productService.getById(id);
    }
}
