package com.integrador5.shopmicroservice.controller;

import com.integrador5.shopmicroservice.DTO.ClientsPurchasesDTO;
import com.integrador5.shopmicroservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping(value = "/reporteComprasClientes")
    public List<ClientsPurchasesDTO> getClientsPurchasesReport(){
        return clientService.getClientsPurchasesReport();
    }

}
