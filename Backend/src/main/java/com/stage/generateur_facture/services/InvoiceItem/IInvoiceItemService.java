package com.stage.generateur_facture.services.InvoiceItem;

import com.stage.generateur_facture.entities.InvoiceItem;

import java.util.List;

public interface IInvoiceItemService {
    List<InvoiceItem> getAllInvoiceItems() ;
    InvoiceItem  createInvoiceItem(InvoiceItem invoiceItem);


}
