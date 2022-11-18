package com.integrador5.shopmicroservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integrador5.shopmicroservice.DTO.MostPopularProductDTO;
import com.integrador5.shopmicroservice.DTO.ProductDTO;
import com.integrador5.shopmicroservice.model.Product;
import com.integrador5.shopmicroservice.model.Purchase;
import com.integrador5.shopmicroservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    public ProductRepository productRepository;

    public void insertProduct(Product p){
        this.productRepository.save(p);
    }

    public List<Product> getAllProduct(){
        return this.productRepository.findAll();
    }

//    public List<ProductDTO> getProductByListOfId(List<Product> toPurchase){
//        List<ProductDTO> temp = new ArrayList<>();
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Bearer "+ "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoidXMsdXNlcjEiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNjY4ODA2MjEyLCJleHAiOjE2Njg4MDY4MTJ9.cfSUwIsGhEJ-KyW5sGXi1yRaaKi-vCYIl-v5tW-Vo2cJvY61-2eEjMEOiqsFPFpZWlDqBvD20a4e8LEPC_cyrw");
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        for(Product p : toPurchase) {
//            Integer id = p.getId();
//            String url = "http://localhost:8080/api/products/id/" + id;
//
//// en caso de que el get no necesite token
//            ResponseEntity<ProductDTO> response = restTemplate.getForEntity(url,ProductDTO.class);
//
//           //en caso de que el get necesite un token
////            HttpEntity<String> entity = new HttpEntity<>(headers);
////            ResponseEntity<ProductDTO> response =
////                    restTemplate.exchange(url,HttpMethod.GET,entity,ProductDTO.class);
//
//            ProductDTO pdto = response.getBody();
//            temp.add(pdto);
//        }
//        return temp;
//    }

    public List<ProductDTO> getProductByListOfId(List<Product> toPurchase){
        RestTemplate restTemplate = new RestTemplate();

        String resourceUrl
                = "http://localhost:8080/api/products/cart";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+ "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoidXMsdXNlcjEiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNjY4ODA3MjUxLCJleHAiOjE2Njg4MDc4NTF9.4DcI3ltIEb0zjtwte-RIrNMmMOnX44BsEWGoDOA09VP8dxJB89vFEF5aktfKry1IkOXmalOjYsepJFwRfXrF0Q");
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<List> request = new HttpEntity<>(toPurchase,headers);
        ResponseEntity<ProductDTO[]> response = restTemplate.exchange(resourceUrl, HttpMethod.POST,request,ProductDTO[].class);

        ObjectMapper mapper = new ObjectMapper();
        ProductDTO[] products= response.getBody();
        return Arrays.stream(products)
                .map(object-> mapper.convertValue(object,ProductDTO.class))
                .collect(Collectors.toList());
    }


    public boolean update (List<ProductDTO> products ) {
        RestTemplate restTemplate = new RestTemplate();

        String resourceUrl
                = "http://localhost:8080/api/products/update/id/list";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+ "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoidXMsdXNlcjEiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNjY4ODA3MjUxLCJleHAiOjE2Njg4MDc4NTF9.4DcI3ltIEb0zjtwte-RIrNMmMOnX44BsEWGoDOA09VP8dxJB89vFEF5aktfKry1IkOXmalOjYsepJFwRfXrF0Q");
        headers.setContentType(MediaType.APPLICATION_JSON);


        HttpEntity<List> request = new HttpEntity<>(products,headers);
        ResponseEntity<String> response = restTemplate.exchange(resourceUrl, HttpMethod.PUT,request,String.class);
        int responsecode = response.getStatusCodeValue();
        System.out.println(responsecode);
        if(responsecode == 200) {
            return true;
        }else{
            return false;
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
                            p.setName(pdto.getName());
                            p.setId_product(pdto.getId_product());
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
       public void updatePurchaseinProduct(List<Product> toPurchase, Purchase purchase){
           for (Product p :toPurchase){
               p.setPurchase(purchase);
               this.insertProduct(p);
           }
       }

       public MostPopularProductDTO getPopularProduct(){
        return this.productRepository.getMostPopularProductReport().get(0);
       }

}
