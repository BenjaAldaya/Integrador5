package com.integrador5.shopmicroservice.service;

import com.integrador5.shopmicroservice.DTO.ProductDTO;
import com.integrador5.shopmicroservice.model.Product;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
public class PurchaseService {

    public void Purchase(List<Product> toPurchase){
       //creo json de los productos a comprar (id_producto y cantidad)
        JSONArray body = crearjson(toPurchase);
        //llamo al servicio de compra
        List<ProductDTO> allProduct = getProducts(body);

        //restarlo , si no hay stock cortarlo
        for (Product p : toPurchase){
            for (ProductDTO pdto : allProduct) {
                if(p.getId() == pdto.getId_producto()){
                    int newstock = pdto.getStock()-p.getQuantity();
                    pdto.setStock(newstock);
                }
            }
        }

        //actualizo con el put(es un endpoint que tiene productos) con ProductoDTO ConfirmProduct(allProduct)
        if(update(allProduct)){
            //creo la compra
        };

    }

    public List<ProductDTO> getProducts(JSONArray body) {
        RestTemplate restTemplate = new RestTemplate();

        String resourceUrl
                = "http://localhost:8080/api/products/";

        ResponseEntity<List>response = restTemplate.exchange(resourceUrl, HttpMethod.GET, new HttpEntity<>(body), List.class);


        // Fetch JSON response as String wrapped in ResponseEntity
        //        ResponseEntity<List> response
        //                = restTemplate.getForEntity(resourceUrl, List.class);

        List<ProductDTO> productsJson = response.getBody();

        System.out.println(productsJson);

        return productsJson;

    }

    public boolean update (List<ProductDTO> products ) {
        RestTemplate restTemplate = new RestTemplate();

        String resourceUrl
                = "http://localhost:8080/api/products/update/id/list";

        HttpEntity<List> request = new HttpEntity<>(products);
        restTemplate.exchange(resourceUrl,HttpMethod.PUT,request,Void.class);

        return true;

    }

    public JSONArray crearjson(List<Product> toPurchase) {
        JSONArray jsonarray = new JSONArray();

        for (Product p : toPurchase) {
            JSONObject json = new JSONObject();
            json.put("product_id", p.getId());
            json.put("quantity", p.getQuantity());
            jsonarray.put(json);
        }

        return jsonarray;
    }
    }
