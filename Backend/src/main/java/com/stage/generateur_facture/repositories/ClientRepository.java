package com.stage.generateur_facture.repositories;

import com.stage.generateur_facture.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {
    List<Client> findByFullNameContainingIgnoreCase(String name);

    Optional<Client> findByEmail(String email);
}
