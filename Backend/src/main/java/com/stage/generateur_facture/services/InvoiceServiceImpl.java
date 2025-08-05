package com.stage.generateur_facture.services;

import com.stage.generateur_facture.entities.Client;
import com.stage.generateur_facture.entities.Invoice;
import com.stage.generateur_facture.entities.InvoiceItem;
import com.stage.generateur_facture.entities.Supplier;
import com.stage.generateur_facture.entities.dto.InvoiceRequest;
import com.stage.generateur_facture.entities.enums.InvoiceStatus;
import com.stage.generateur_facture.repositories.InvoiceItemRepository;
import com.stage.generateur_facture.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements IInvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceItemRepository invoiceItemRepository;
    private final ClientServiceImpl clientService;
    private final SupplierServiceImpl supplierService;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceItemRepository invoiceItemRepository, ClientServiceImpl clientService, SupplierServiceImpl supplierService) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceItemRepository = invoiceItemRepository;
        this.clientService = clientService;
        this.supplierService = supplierService;
    }

    @Override
    public List<Invoice> getAllInvoice() {
        return invoiceRepository.findAll();
    }

    private Client findOrCreateClient(Client client) {
        return clientService.findByEmail(client.getEmail())
                .orElseGet(() -> clientService.createClient(client));
    }


    private Supplier findOrCreateSupplier(Supplier supplier) {
        List<Supplier> existing = supplierService.searchSupplier(supplier.getSuppname());
        if (!existing.isEmpty()) {
            return existing.get(0); // or apply more precise filtering if needed
        }
        return supplierService.createSupplier(supplier);
    }


    @Override
    public Invoice createInvoice(InvoiceRequest request) {
        Client client = findOrCreateClient(request.getClient());
        Supplier supplier = findOrCreateSupplier(request.getSupplier());

        Invoice invoice = new Invoice();
        invoice.setTitle(request.getTitle());
        invoice.setClient(client);
        invoice.setSupplier(supplier);
        invoice.setDate(request.getInvoiceDate());
        invoice.setTotal(BigDecimal.valueOf(request.getTotalAmount()));
        invoice.setStatus(InvoiceStatus.PENDING);
        invoice = invoiceRepository.save(invoice);

        List<InvoiceItem> savedItems = new ArrayList<>();
        for (InvoiceItem item : request.getItems()) {
            item.setInvoice(invoice);
            InvoiceItem savedItem = invoiceItemRepository.save(item);
            savedItems.add(savedItem);
        }
        invoice.setInvoiceItems(savedItems); // 👈 Add this line

        return invoice;
    }



    @Override
    public Optional<Invoice> getInvoiceById(Long id) {
        return invoiceRepository.findById(id);
    }

    @Override
    public void deleteInvoice(Long id) {
    invoiceRepository.deleteById(id);
    }


}
