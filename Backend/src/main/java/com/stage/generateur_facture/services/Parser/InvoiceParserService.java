package com.stage.generateur_facture.services.Parser;

import com.stage.generateur_facture.entities.*;
import com.stage.generateur_facture.entities.dto.InvoiceRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class InvoiceParserService {

    public InvoiceRequest parseInvoiceText(String text) {
        InvoiceRequest invoice = new InvoiceRequest();
        Client client = new Client();
        Supplier supplier = new Supplier();
        List<InvoiceItem> items = new ArrayList<>();

        String[] lines = text.split("\\r?\\n");

        // Patterns
        Pattern invoiceNoPattern = Pattern.compile("INVOICE NO:\\s*(\\d+)", Pattern.CASE_INSENSITIVE);
        Pattern datePattern = Pattern.compile("DATE:\\s*(\\d{2}\\.\\d{2}\\.\\d{4})", Pattern.CASE_INSENSITIVE);
        Pattern taxPattern = Pattern.compile("Tax\\s*(\\d+)%", Pattern.CASE_INSENSITIVE);
        Pattern totalPattern = Pattern.compile("TOTAL\\s*\\$?(\\d+(?:\\.\\d{1,2})?)", Pattern.CASE_INSENSITIVE);
        Pattern itemPattern = Pattern.compile("^(.*?)\\s+(\\d+(?:\\.\\d{1,2})?)\\s+(\\d+)\\s+\\$?(\\d+(?:\\.\\d{1,2})?)$");

        boolean inItemsSection = false;

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();

            // --- Invoice number ---
            Matcher mInvoice = invoiceNoPattern.matcher(line);
            if (mInvoice.find()) {
                invoice.setTitle("Invoice " + mInvoice.group(1));
            }

            // --- Date ---
            Matcher mDate = datePattern.matcher(line);
            if (mDate.find()) {
                invoice.setDate(LocalDate.parse(mDate.group(1), DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            }

            // --- Client name ---
            if (line.toUpperCase().startsWith("ISSUED TO:") && i + 1 < lines.length) {
                client.setFullName(lines[i + 1].trim());
            }

            // --- Supplier name ---
            if (line.toUpperCase().startsWith("DUE DATE:") && i + 1 < lines.length) {
                supplier.setSuppname(lines[i + 1].trim());
            }

            // --- Client address ---
            if (client.getFullName() != null && !client.getFullName().isEmpty() &&
                    line.equals(client.getFullName()) && i + 1 < lines.length) {
                client.setAddress(lines[i + 1].trim());
            }

            // --- Tax ---
            Matcher mTax = taxPattern.matcher(line);
            if (mTax.find()) {
                invoice.setTax(new BigDecimal(mTax.group(1)));
            }

            // --- Total ---
            Matcher mTotal = totalPattern.matcher(line);
            if (mTotal.find()) {
                invoice.setTotal(new BigDecimal(mTotal.group(1)));
            }

            // --- Detect start of items table ---
            if (line.toUpperCase().startsWith("DESCRIPTION")) {
                inItemsSection = true;
                continue;
            }
            // --- Detect end of items table ---
            if (line.toUpperCase().startsWith("SUBTOTAL") ||
                    line.toUpperCase().startsWith("TOTAL") ||
                    line.toUpperCase().startsWith("TAX")) {
                inItemsSection = false;
            }

            // --- Parse items ---
            if (inItemsSection) {
                // Skip headers or bank details
                if (line.isEmpty() ||
                        line.toUpperCase().contains("ACCOUNT") ||
                        line.toUpperCase().contains("BANK") ||
                        line.toUpperCase().contains("PAY TO")) {
                    continue;
                }

                Matcher mItem = itemPattern.matcher(line);
                if (mItem.find()) {
                    InvoiceItem item = new InvoiceItem();
                    item.setItem_name(mItem.group(1).trim());
                    item.setUnit_price(Float.parseFloat(mItem.group(2)));
                    item.setQuantity(Integer.parseInt(mItem.group(3)));
                    item.setTotal_price(Float.parseFloat(mItem.group(4)));
                    items.add(item);
                }
            }
        }

        // Assign relationships
        invoice.setClient(client);
        invoice.setSupplier(supplier);
        invoice.setInvoiceItems(items);

        return invoice;
    }
}
