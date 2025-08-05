package com.stage.generateur_facture.repositories;

import com.stage.generateur_facture.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier,Long> {

    List<Supplier> findBySuppnameContainingIgnoreCase(String name);
}
