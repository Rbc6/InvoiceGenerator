package com.stage.generateur_facture.entities.dto;

import com.stage.generateur_facture.entities.Client;
import com.stage.generateur_facture.entities.InvoiceItem;
import com.stage.generateur_facture.entities.Supplier;
import com.stage.generateur_facture.entities.enums.InvoiceStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class InvoiceRequest {

    private String title;
    private LocalDate date;
    private BigDecimal total;
    private String notes;
    private BigDecimal tax;
    private BigDecimal discount;
    private BigDecimal shipping;
    private InvoiceStatus status;
    private Client client;
    private Supplier supplier;
    private List<InvoiceItem> invoiceItems;
}
