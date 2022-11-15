package com.integrador5.productmicroservice.service;

import com.integrador5.productmicroservice.DTO.ProductDTO;
import com.integrador5.productmicroservice.models.Product;
import com.integrador5.productmicroservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return this.productRepository.findAll();
    }


    public void insertProduct(Product product){
        this.productRepository.save(product);
    }


    public void deleteProduct(Integer id){
        this.productRepository.deleteById(id);
    }

    public Product updateProduct( Integer id, Product product){
        return this.productRepository.findById(id)
                .map(oldProduct -> {
                    oldProduct.setName(product.getName());
                    oldProduct.setDescription(product.getDescription());
                    oldProduct.setPrice(product.getPrice());
                    oldProduct.setStock(product.getStock());
                    return this.productRepository.save(oldProduct);
                })
                .orElseGet(() -> {
                    return this.productRepository.save(product);
                });
    }

    public List<Product> findCanBuy(List<ProductDTO> products) {
        List<Product> productList = new ArrayList<Product>();
        for(ProductDTO dto: products){
            Product tmp = this.productRepository.getById(dto.getProduct_id());
            if(tmp != null && tmp.getStock() >= dto.getQuantity()){
                productList.add(tmp);
            }
        }
        return productList;
    }

    public void updateProductByList(List<Product> products) {
        for(Product p: products){
            this.updateProduct(p.getId_product(), p);
        }
    }

    public Optional<Product> getById(Integer id) {
        return productRepository.findById(id);
    }
}
