package com.stage.generateur_facture.controllers;

import com.stage.generateur_facture.entities.Client;
import com.stage.generateur_facture.services.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final IClientService clientService;

    // GET /clients/search?name=Ali
    @GetMapping("/search")
    public List<Client> searchClients(@RequestParam String name) {
        return clientService.searchClients(name);
    }

    // POST /clients
    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }
}
