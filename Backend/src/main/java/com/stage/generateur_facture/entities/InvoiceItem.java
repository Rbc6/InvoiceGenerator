package com.stage.generateur_facture.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String item_name;
    private String description;
    private Integer quantity;
    private Float unit_price;
    private Float total_price;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

}
