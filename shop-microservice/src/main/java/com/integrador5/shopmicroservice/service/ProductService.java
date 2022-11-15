package com.integrador5.shopmicroservice.service;

import com.integrador5.shopmicroservice.model.Product;
import com.integrador5.shopmicroservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    public ProductRepository productRepository;

    public void insertProduct(Product p){
        this.productRepository.save(p);
    }
}
