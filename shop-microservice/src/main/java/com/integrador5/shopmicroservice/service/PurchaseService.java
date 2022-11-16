package com.integrador5.shopmicroservice.service;

import com.integrador5.shopmicroservice.DTO.ProductDTO;
import com.integrador5.shopmicroservice.model.Client;
import com.integrador5.shopmicroservice.model.Product;
import com.integrador5.shopmicroservice.model.Purchase;
import com.integrador5.shopmicroservice.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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


    public void purchase (List<Product> toPurchase ,Integer idClient){
        Client client = clientService.getById(idClient);
            List<ProductDTO> allProduct = this.productService.getProductByListOfId(toPurchase);
            List<ProductDTO> listProductDto = this.productService.searchAndUpdate(toPurchase, allProduct);
            if (listProductDto != null) {
                if (this.productService.update(listProductDto)) {

                    Date date = new Date();
                    SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
                    float totalprice = getTotalPrice(toPurchase, listProductDto);
                    Purchase purchase = new Purchase(DateFor.format(date), totalprice, client);
                    Integer purchaseid = this.insertPurchase(purchase);
                    purchase.setId(purchaseid);

                    this.productService.updatePurchaseinProduct(toPurchase, purchase);

//                    purchase.setProductList(toPurchase);

//                    this.updatePurchase(purchase.getId(), purchase);
//                    clientService.updatePurchases(client, purchase);
                    System.out.println("se hizo la compra");
                }
            } else {
                System.out.println("NO se hizo la compra");
            }
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




}
