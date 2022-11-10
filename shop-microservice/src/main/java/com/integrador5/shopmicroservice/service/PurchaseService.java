package com.integrador5.shopmicroservice.service;

import com.integrador5.shopmicroservice.DTO.ProductDTO;
import com.integrador5.shopmicroservice.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PurchaseService {

    public void Purchase(List<Product> toPurchase){
        List<ProductDTO> allProduct = getProducts(toPurchase);
        //comprobar Stock y restarlo , si no hay stock cortarlo
        //actualizo con el put(es un endpoint que tiene productos) con ProductoDTO updateProduct(tempPruductDTO)
        //creo la compra
    }

    public List<ProductDTO> getProducts(List<Product> toPurchase) {
        RestTemplate restTemplate = new RestTemplate();

        String resourceUrl
                = "http://localhost:8080/products";

        // Fetch JSON response as String wrapped in ResponseEntity
        ResponseEntity<List> response
                = restTemplate.getForEntity(resourceUrl, List.class);

        List<ProductDTO> productsJson = response.getBody();

        System.out.println(productsJson);

        return productsJson;

    }
}
