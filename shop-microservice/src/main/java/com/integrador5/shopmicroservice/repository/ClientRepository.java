package com.integrador5.shopmicroservice.repository;

import com.integrador5.shopmicroservice.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query("SELECT c.id, c.name, p.price FROM Client c JOIN c.purchases p)")
    public float getClientsPurchasesPrice();
}
