package com.integrador5.productmicroservice.service;

import com.integrador5.productmicroservice.models.Product;
import com.integrador5.productmicroservice.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ProductService {

    ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return this.productRepository.findAll();
    }


    public void insertProduct(Product product){
        this.productRepository.save(product);
    }


    public void deleteProduct(Integer id){
        this.productRepository.deleteById(id);
    }

    public Product updateProduct( Integer id, Product product){
        return this.productRepository.findById(id)
                .map(oldProduct -> {
                    oldProduct.setName(product.getName());
                    oldProduct.setDescription(product.getDescription());
                    oldProduct.setPrice(product.getPrice());
                    oldProduct.setStock(product.getStock());
                    return this.productRepository.save(oldProduct);
                })
                .orElseGet(() -> {
                    return this.productRepository.save(product);
                });
    }
}
