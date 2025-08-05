package com.stage.generateur_facture.services;

import com.stage.generateur_facture.entities.InvoiceItem;
import com.stage.generateur_facture.entities.Supplier;
import com.stage.generateur_facture.repositories.InvoiceItemRepository;

import java.util.List;

public class InvoiceItemService implements IInvoiceItemService {

    private final InvoiceItemRepository invoiceItemRepository;

    public InvoiceItemService(InvoiceItemRepository invoiceItemRepository) {
        this.invoiceItemRepository = invoiceItemRepository;
    }


    @Override
    public List<InvoiceItem> getAllInvoiceItems() {
        return invoiceItemRepository.findAll();
    }

    @Override
    public InvoiceItem createInvoiceItem(InvoiceItem invoiceItem) {
        return invoiceItemRepository.save(invoiceItem);
    }

}
