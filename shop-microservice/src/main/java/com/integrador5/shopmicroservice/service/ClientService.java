package com.integrador5.shopmicroservice.service;

import com.integrador5.shopmicroservice.DTO.ClientsPurchasesDTO;
import com.integrador5.shopmicroservice.model.Client;
import com.integrador5.shopmicroservice.model.Purchase;
import com.integrador5.shopmicroservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients(){
        return this.clientRepository.findAll();
    }

    public void insertClient(Client newClient){ this.clientRepository.save(newClient); }

    public void deleteClient(int id){
        this.clientRepository.deleteById(id);
    }

    public Client updateClient(Integer id_client, Client c) {
        return this.clientRepository.findById(id_client)
                .map(oldClient -> {
                    oldClient.setName(c.getName());
                    oldClient.setPurchaseslist(c.getPurchaseslist());
                    return this.clientRepository.save(oldClient);
                })
                .orElseGet(() -> {
                    return this.clientRepository.save(c);
                });
    }

    public void updateClientByList(List<Client> clients) {
        for(Client c: clients){
            this.updateClient(c.getId_client(), c);
        }
    }

    /**
     * Obtiene un reporte de los clientes y el monto total de sus compras
     * @return lista del tipo dto
     */
    public List<ClientsPurchasesDTO> getClientsPurchasesReport() {
//        List<Purchase> clientPurchases = clientRepository.getClientPurchases();
        List<ClientsPurchasesDTO> report = new ArrayList<>();
        return report;
    }

    public Client getById(Integer Id){
        return this.clientRepository.getById(Id);
    }
}
