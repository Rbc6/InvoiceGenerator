package com.stage.generateur_facture.repositories;

import com.stage.generateur_facture.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
}
