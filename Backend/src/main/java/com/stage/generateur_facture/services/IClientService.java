package com.stage.generateur_facture.services;

import com.stage.generateur_facture.entities.Client;

import java.util.List;
import java.util.Optional;

public interface IClientService {

    List<Client> getAllClients();

    Optional<Client> getClientById(Long id) ;

    Client createClient(Client medecin);

    Client updateClient(Long id, Client medecin);

    void deleteClient(Long id) ;
    List<Client> searchClients(String name);
}

