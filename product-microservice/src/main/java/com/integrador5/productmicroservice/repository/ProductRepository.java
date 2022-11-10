package com.integrador5.productmicroservice.repository;

import com.integrador5.productmicroservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
