package com.integrador5.productmicroservice.controller;

import com.integrador5.productmicroservice.DTO.ProductDTO;
import com.integrador5.productmicroservice.models.Product;
import com.integrador5.productmicroservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@Tag(name="Product", description="Products related resources")
@RequestMapping(value = "/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(value = "/")
    @Operation(summary="Get all products ", description="List of products")
    public List<Product> getAllProducts(){
        return this.productService.getAllProducts();
    }

    @PostMapping(value = "/insert")
    @Operation(summary="Insert a product", description="Insert a new product")
    public void insertarProducto(@RequestBody Product product){
        this.productService.insertProduct(product);
    }


    @DeleteMapping(value = "/delete/id/{id}")
    @Operation(summary="Delete a product", description="Delete a product by id")
    public void deleteProduct(@PathVariable Integer id){
        this.productService.deleteProduct(id);
    }

    @PutMapping(value="/update/id/{id}")
    @Operation(summary="Update product", description="Update a product by id")
    public void updateProduct(@PathVariable Integer id, @RequestBody Product product){
        this.productService.updateProduct(id,product);
    }

    @PutMapping(value="/update/id/list")
    @Operation(summary="Update products", description="Update a list of products")
    public void updateProductByList(@RequestBody List<Product> product){
        this.productService.updateProductByList(product);
    }

    @PostMapping(value="/cart")
    @Operation(summary="Cart", description="Cart of products to buy")
    public List<Product> canBuyThem(@RequestBody List<ProductDTO> products){
        return this.productService.findCanBuy(products);
    }

    @GetMapping(value = "/id/{id}")
    @Operation(summary="Get product", description="Get product by id")
    public Optional<Product> getById(@PathVariable Integer id){
        return this.productService.getById(id);
    }
}
