package com.integrador5.shopmicroservice.repository;

import com.integrador5.shopmicroservice.DTO.MostPopularProductDTO;
import com.integrador5.shopmicroservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

 @Query(value="SELECT new com.integrador5.shopmicroservice.DTO.MostPopularProductDTO(p.id_product,p.name ,SUM(p.quantity)) FROM Product p join Purchase c on c.id = p.purchase.id group by p.id_product,p.name order by SUM(p.quantity) desc" )
   // @Query(value = "SELECT p.id,p.name ,SUM(p.quantity) FROM Product p group by p.id,p.name",nativeQuery = true)
    List<MostPopularProductDTO> getMostPopularProductReport();
}



// compra entrar en la lista de productos y sumar las cantidades agrupando el producto por id
