package com.stage.generateur_facture.services.Invoice;

import com.stage.generateur_facture.entities.Invoice;
import com.stage.generateur_facture.entities.dto.InvoiceRequest;

import java.util.List;
import java.util.Optional;

public interface IInvoiceService {

    List<Invoice> getAllInvoice();
    Invoice createInvoice(InvoiceRequest invoice);
    Optional<Invoice> getInvoiceById(Long id) ;
    void deleteInvoice(Long id) ;

}
