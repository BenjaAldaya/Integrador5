package com.integrador5.clientmicroservice.service;
import com.integrador5.clientmicroservice.model.Client;
import com.integrador5.clientmicroservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Client getById(Integer Id){
        return this.clientRepository.getReferenceById(Id);
    }
}
