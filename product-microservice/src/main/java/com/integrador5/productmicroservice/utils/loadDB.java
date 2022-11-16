package com.integrador5.productmicroservice.utils;

import com.integrador5.productmicroservice.controller.ProductController;
import com.integrador5.productmicroservice.models.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class loadDB {

    @Bean
    CommandLineRunner initDatabase(ProductController pc){
        return args -> {
            Product p1 = new Product("Harina", "Bajo en sodio", 3.2, 300);
            Product p2 = new Product("Coca Cola Light", "Bajo en azucares", 3.2, 300);
            pc.insertarProducto(p1);
            pc.insertarProducto(p2);
        };
    }


}
