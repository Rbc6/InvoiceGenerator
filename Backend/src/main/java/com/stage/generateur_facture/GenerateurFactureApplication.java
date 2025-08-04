package com.stage.generateur_facture;

import com.stage.generateur_facture.entities.Client;
import com.stage.generateur_facture.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GenerateurFactureApplication {

    public static void main(String[] args) {
        SpringApplication.run(GenerateurFactureApplication.class, args);
    }

/*
    @Bean
    CommandLineRunner run(ClientRepository clientRepository) {

        return args -> {
            Client client = Client.builder()
                    .full_name("Test Client")
                    .email("test@example.com")
                    .phone_number("123456789")
                    .address("Test Address")
                    .build();

            clientRepository.save(client);

            System.out.println("✅ Client saved with ID: " + client.getId());
        };
    }*/
}
