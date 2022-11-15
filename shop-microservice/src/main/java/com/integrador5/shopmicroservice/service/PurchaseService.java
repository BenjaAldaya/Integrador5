package com.integrador5.shopmicroservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integrador5.shopmicroservice.DTO.ProductDTO;
import com.integrador5.shopmicroservice.controller.ClientController;
import com.integrador5.shopmicroservice.model.Client;
import com.integrador5.shopmicroservice.model.Product;
import com.integrador5.shopmicroservice.model.Purchase;
import com.integrador5.shopmicroservice.repository.PurchaseRepository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.http.HttpClient;
//import org.springframework.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ClientService clientService;

    public List<Purchase> getAllPurchases(){
        return this.purchaseRepository.findAll();
    }

    public Integer insertPurchase(Purchase newPurchase){
        return this.purchaseRepository.save(newPurchase).getId();
    }

    public void deletePurchase(Integer id){
        this.purchaseRepository.deleteById(id);
    }

    public Purchase updatePurchase(Integer id_purchase, Purchase p) {
        return this.purchaseRepository.findById(id_purchase)
                .map(oldClient -> {
                    oldClient.setDate(p.getDate());
                    oldClient.setPrice(p.getPrice());
                    oldClient.setProductList(p.getProductList());
                    oldClient.setClient(p.getClient());
                    return this.purchaseRepository.save(oldClient);
                })
                .orElseGet(() -> {
                    return this.purchaseRepository.save(p);
                });
    }

//    public void purchase(List<Product> toPurchase){
//       //creo json de los productos a comprar (id_producto y cantidad)
//        JSONArray body = crearjson(toPurchase);
//        System.out.println(body.toList());
//        //llamo al servicio de productos y me trae los productos que solicite y tienen stock disponible
//        List<ProductDTO> allProduct = getProducts(body);
//
//        //recorrer y restar el stock
//        for (Product p : toPurchase){
//            for (ProductDTO pdto : allProduct) {
//                if(p.getId() == pdto.getId_producto()){
//                   int newstock = pdto.getStock()-p.getQuantity();
//                   pdto.setStock(newstock);
//              }
//            }
//        }

    public void purchase (List<Product> toPurchase ,Integer idClient){
        Client client = clientService.getById(idClient);
        List<ProductDTO> allProduct = getProductByListOfId(toPurchase);
        List<ProductDTO> listProductDto = searchAndUpdate(toPurchase,allProduct);
        if(listProductDto != null){
            if(update(listProductDto)){
                //informacion de la compra
                Date date = new Date();
                SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
                float totalprice = getTotalPrice(toPurchase,listProductDto);
                //creo la compra
                Purchase purchase = new Purchase(DateFor.format(date),totalprice, client);
                //la persisto
                Integer purchaseid = this.insertPurchase(purchase);
                purchase.setId(purchaseid);
                //actualizo los productos con la compra
                for (Product p :toPurchase){
                    p.setPurchase(purchase);
                    this.productService.insertProduct(p);
                }

                purchase.setProductList(toPurchase);
                this.updatePurchase(purchase.getId(),purchase);
//                System.out.println(this.getAllPurchases());
                clientService.updatePurchases(client,purchase);
//                System.out.println(client);
                System.out.println("se hizo la compra");
            }
        }else{
            System.out.println("NO se hizo la compra");
        }
    }

    public List<ProductDTO> searchAndUpdate(List<Product> toPurchase,List<ProductDTO> allProduct){
        List<ProductDTO> listProduct = new ArrayList<>();
        boolean existe = false;
        for (Product p : toPurchase){
            for (ProductDTO pdto : allProduct) {
                if (pdto != null) {
                    if (p.getId() == pdto.getId_product()) {
                        if (p.getQuantity() <= pdto.getStock() && p.getQuantity() <= 3) { // p.getQuantity <= 3 se sustituye por un metodo que revise las compras del cliente por dia y cantidad
                            pdto.setStock(pdto.getStock() - p.getQuantity());
                            listProduct.add(pdto);
                            //agregar al precio total

                            existe = true;
                            break;
                        } else {
                            System.out.println("no hay stock");
                            return null;
                        }
                    } else {
                        existe = false;
                    }
                }else{
                    System.out.println("el producto no existe");
                    return null;
                }
            }
            if(!existe){
                System.out.println("el producto no existe");
                return null;
            }
        }
        return listProduct;
    }

//    public List<ProductDTO> getAllProduct(){
//        RestTemplate restTemplate = new RestTemplate();
//
//        String resourceUrl
//                = "http://localhost:8080/api/products/";
//
//        // Fetch response as List wrapped in ResponseEntity
//        ResponseEntity<ProductDTO[]> response
//                = restTemplate.getForEntity(resourceUrl, ProductDTO[].class);
//
//        ObjectMapper mapper = new ObjectMapper();
//        ProductDTO[] products= response.getBody();
//        return Arrays.stream(products)
//                .map(object-> mapper.convertValue(object,ProductDTO.class))
//                .collect(Collectors.toList());
//    }

    public List<ProductDTO> getProductByListOfId(List<Product> toPurchase){
        List<ProductDTO> temp = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        for(Product p : toPurchase) {
            Integer id = p.getId();
            String url = "http://localhost:8080/api/products/id/" + id;

            ResponseEntity<ProductDTO> response =
                    restTemplate.getForEntity(url,ProductDTO.class);

            ProductDTO pdto = response.getBody();
            temp.add(pdto);
        }
        return temp;
    }


//    public List<ProductDTO> getProducts(JSONArray body) {
//        RestTemplate restTemplate = new RestTemplate();
//
//        String resourceUrl
//                = "http://localhost:8080/api/products/cart";
//
//        HttpEntity<JSONArray> jsonbody = new HttpEntity<>(body);
//        ResponseEntity<List>response = restTemplate.exchange(resourceUrl, HttpMethod.GET,jsonbody, List.class);


        // Fetch JSON response as String wrapped in ResponseEntity
        //        ResponseEntity<List> response
        //                = restTemplate.getForEntity(resourceUrl, List.class);

//        List<ProductDTO> productsJson = response.getBody();
//
//        System.out.println(productsJson);
//        return productsJson;
//
//    }

    public boolean update (List<ProductDTO> products ) {
        RestTemplate restTemplate = new RestTemplate();

        String resourceUrl
                = "http://localhost:8080/api/products/update/id/list";

        HttpEntity<List> request = new HttpEntity<>(products);
        ResponseEntity<String> response = restTemplate.exchange(resourceUrl,HttpMethod.PUT,request,String.class);
        int responsecode = response.getStatusCodeValue();
        if(responsecode == 200) {
            return true;
        }else{
            return false;
        }

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

    public float getTotalPrice(List<Product> toPurchase, List<ProductDTO> listProduct){
        float totalprice = 0 ;
        for (Product p : toPurchase){
            for (ProductDTO pdto :listProduct){
                if(p.getId() == pdto.getId_product()){
                    totalprice += pdto.getPrice() * p.getQuantity();
                    break;
                }
            }
        }
        return totalprice;
    }

}
