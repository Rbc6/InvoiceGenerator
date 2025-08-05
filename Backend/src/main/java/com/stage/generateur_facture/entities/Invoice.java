package com.stage.generateur_facture.entities;

import com.stage.generateur_facture.entities.enums.InvoiceStatus;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Temporal(TemporalType.DATE)
    private LocalDate date;

    private BigDecimal total;

    private String notes;
    private BigDecimal tax;
    private BigDecimal discount;
    private BigDecimal shipping;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InvoiceStatus status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InvoiceItem> invoiceItems;

    private String code;

    @PostPersist
    public void generateCode() {
        this.code = String.format("INV%03d", this.id);
    }

    public String getInvoiceCode() {
        return String.format("INV%03d", this.id);
    }


}
