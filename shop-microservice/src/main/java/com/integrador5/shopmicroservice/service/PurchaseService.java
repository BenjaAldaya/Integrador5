package com.integrador5.shopmicroservice.service;

import com.integrador5.shopmicroservice.DTO.ProductDTO;
import com.integrador5.shopmicroservice.model.Product;
import com.integrador5.shopmicroservice.model.Purchase;
import com.integrador5.shopmicroservice.repository.PurchaseRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.http.HttpClient;
//import org.springframework.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
public class PurchaseService {

    /**
     * inyeccion de capa de configuracion
     */
    @Autowired
    private RestTemplate clientRest;

    @Autowired
    private PurchaseRepository purchaseRepository;

    public List<Purchase> getAllPurchases(){
        return this.purchaseRepository.findAll();
    }

    public void insertPurchase(Purchase newPurchase){
        this.purchaseRepository.save(newPurchase);
    }

    public void deletePurchase(Integer id){
        this.purchaseRepository.deleteById(id);
    }

    public void purchase(List<Product> toPurchase){
       //creo json de los productos a comprar (id_producto y cantidad)
        JSONArray body = crearjson(toPurchase);
        System.out.println(body.toList());
        //llamo al servicio de productos y me trae los productos que solicite y tienen stock disponible
        List<ProductDTO> allProduct = getProducts(body);

        //recorrer y restar el stock
//        for (Product p : toPurchase){
//            for (ProductDTO pdto : allProduct) {
//                if(p.getId() == pdto.getId_producto()){
//                    int newstock = pdto.getStock()-p.getQuantity();
//                    pdto.setStock(newstock);
//                }
//            }
        }

        //actualizo con el put con ProductoDTO
//        if(update(allProduct)){
//            //creo la compra
//        };

//    }

    public List<ProductDTO> getProducts(JSONArray body) {
        RestTemplate restTemplate = new RestTemplate();

        String resourceUrl
                = "http://localhost:8080/api/products/cart";

        HttpEntity<JSONArray> jsonbody = new HttpEntity<>(body);
        ResponseEntity<List>response = restTemplate.exchange(resourceUrl, HttpMethod.GET,jsonbody, List.class);


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
