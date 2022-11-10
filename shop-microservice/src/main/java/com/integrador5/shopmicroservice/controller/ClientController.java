package com.integrador5.shopmicroservice.controller;

import com.integrador5.shopmicroservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

    @Autowired
    ClientService clientService;


}
