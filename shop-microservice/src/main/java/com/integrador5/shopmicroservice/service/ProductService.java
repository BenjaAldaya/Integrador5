package com.integrador5.shopmicroservice.service;

import com.integrador5.shopmicroservice.DTO.MostPopularProductDTO;
import com.integrador5.shopmicroservice.DTO.ProductDTO;
import com.integrador5.shopmicroservice.model.Product;
import com.integrador5.shopmicroservice.model.Purchase;
import com.integrador5.shopmicroservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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

    public boolean update (List<ProductDTO> products ) {
        RestTemplate restTemplate = new RestTemplate();

        String resourceUrl
                = "http://localhost:8080/api/products/update/id/list";

        HttpEntity<List> request = new HttpEntity<>(products);
        ResponseEntity<String> response = restTemplate.exchange(resourceUrl, HttpMethod.PUT,request,String.class);
        int responsecode = response.getStatusCodeValue();
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
//        @Transactional
//       public Page<MostPopularProductDTO> getPopularProduct(){
//        return this.productRepository.getMostPopularProductReport(PageRequest.of(0,1));
//       }

       public List<MostPopularProductDTO> getPopularProduct(){
        return this.productRepository.getMostPopularProductReport();
       }

}
