package com.integrador5.clientmicroservice.controller;

import com.integrador5.clientmicroservice.model.Client;
import com.integrador5.clientmicroservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping(value="/")
    public List<Client> getAllClients(){
        return this.clientService.getAllClients();
    }

    @PostMapping(value="/insert" )
    public void insertClient(@RequestBody Client c) {
        this.clientService.insertClient(c);
    }

    @DeleteMapping(value = "/delete/id/{id}")
    public void deleteClient(@PathVariable int id){
        this.clientService.deleteClient(id);
    }

    @PutMapping(value="/update/id/{id}")
    public void updateClient(@PathVariable Integer id, @RequestBody Client client){
        this.clientService.updateClient(id,client);
    }

    @PutMapping(value="/update/id/list")
    public void updateClientByList(@RequestBody List<Client> client){
        this.clientService.updateClientByList(client);
    }

    @GetMapping(value = "/id/{id}")
    public Client getById (@PathVariable Integer Id){
        return this.clientService.getById(Id);
    }

}
