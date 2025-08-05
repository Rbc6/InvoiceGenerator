package com.stage.generateur_facture.services;

import com.stage.generateur_facture.entities.Invoice;
import com.stage.generateur_facture.entities.InvoiceItem;
import com.stage.generateur_facture.entities.Supplier;

import java.util.List;
import java.util.Optional;

public interface IInvoiceItemService {
    List<InvoiceItem> getAllInvoiceItems() ;
    InvoiceItem  createInvoiceItem(InvoiceItem invoiceItem);


}
