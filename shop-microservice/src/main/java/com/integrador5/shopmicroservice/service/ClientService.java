package com.integrador5.shopmicroservice.service;

import com.integrador5.shopmicroservice.DTO.ClientsPurchasesDTO;
import com.integrador5.shopmicroservice.model.Client;
import com.integrador5.shopmicroservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients(){
        return this.clientRepository.findAll();
    }

    public void insertClient(Client newClient){
        this.clientRepository.save(newClient);
    }

    public void deleteClient(int id){
        this.clientRepository.deleteById(id);
    }

    /**
     * Obtiene un reporte de los clientes y el monto total de sus compras
     * @return lista del tipo dto
     */
    public List<ClientsPurchasesDTO> getClientsPurchasesReport() {
        List<Client> clients = clientRepository.findAll();
        List<ClientsPurchasesDTO> report = new ArrayList<>();

        for(Client c : clients){
            ClientsPurchasesDTO newDTO = new ClientsPurchasesDTO(c.getName(), c.getTotalPricePurchases());
            report.add(newDTO);
        }
        return report;
    }
}
