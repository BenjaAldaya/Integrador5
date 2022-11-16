package com.integrador5.shopmicroservice.repository;

import com.integrador5.shopmicroservice.DTO.PurchasesPerDayDTO;
import com.integrador5.shopmicroservice.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

    //TODO: probar
    @Query("SELECT p.date, SUM(p.id), SUM(p.price) FROM Purchase p GROUP BY p.date") 
    public List<PurchasesPerDayDTO> getPurchasesPerDayReport();


}
