package com.stage.generateur_facture.entities.dto;

import com.stage.generateur_facture.entities.Client;
import com.stage.generateur_facture.entities.InvoiceItem;
import com.stage.generateur_facture.entities.Supplier;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class InvoiceRequest {
    private String title;
    private Client client;
    private Supplier supplier;
    private List<InvoiceItem> items;
    private LocalDate invoiceDate;
    private double totalAmount;
}
