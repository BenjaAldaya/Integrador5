package com.integrador5.shopmicroservice.utils;

import com.integrador5.shopmicroservice.controller.ClientController;
import com.integrador5.shopmicroservice.model.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class CargarDB {
    @Bean
    CommandLineRunner initDatabase(ClientController ctrlCarrera) {
        return args -> {
            Client c1 = new Client("Aldaya Benjamin");
            Client c2 = new Client("Binetti Facundo");
            Client c3 = new Client("Fernandez Manuela");
            Client c4 = new Client("Carrera Tomas");
            Client c5 = new Client("Rust Matias");
            ctrlCarrera.insertClient(c1);
            ctrlCarrera.insertClient(c2);
            ctrlCarrera.insertClient(c3);
            ctrlCarrera.insertClient(c4);
            ctrlCarrera.insertClient(c5);
            System.out.println("Clientes cargados");
        };
    }
}
