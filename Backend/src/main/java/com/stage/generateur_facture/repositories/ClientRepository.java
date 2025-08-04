package com.stage.generateur_facture.repositories;

import com.stage.generateur_facture.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {
    List<Client> findByFullNameContainingIgnoreCase(String name);
}
