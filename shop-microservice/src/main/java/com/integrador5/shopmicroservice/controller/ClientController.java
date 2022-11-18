package com.integrador5.shopmicroservice.controller;

import com.integrador5.shopmicroservice.model.Client;
import com.integrador5.shopmicroservice.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name="Client", description="Client related resources")
@RequestMapping(value = "/api/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping(value="/")
    @Operation(summary="Get all clients ", description="List of clients")
    public List<Client> getAllClients(){
        return this.clientService.getAllClients();
    }

    @PostMapping(value="/insert" )
    @Operation(summary="Insert a client ", description="Add a new client")
    public void insertClient(@RequestBody Client c) {
        this.clientService.insertClient(c);
    }

    @DeleteMapping(value = "/delete/id/{id}")
    @Operation(summary="Delete a client ", description="Delete a client by id")
    public void deleteClient(@PathVariable int id){
        this.clientService.deleteClient(id);
    }

    @PutMapping(value="/update/id/{id}")
    @Operation(summary="Update client ", description="Update a client by id")
    public void updateClient(@PathVariable Integer id, @RequestBody Client client){
        this.clientService.updateClient(id,client);
    }

    @PutMapping(value="/update/id/list")
    @Operation(summary="Update clients ", description="Update a list of clients")
    public void updateClientByList(@RequestBody List<Client> client){
        this.clientService.updateClientByList(client);
    }


    @GetMapping(value = "/id/{id}")
    @Operation(summary="Get client ", description="Get a client by id")
    public Optional<Client> getById (@PathVariable Integer id){
        return this.clientService.findById(id);
    }


}
