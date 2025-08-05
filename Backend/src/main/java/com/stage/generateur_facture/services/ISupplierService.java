package com.stage.generateur_facture.services;


import com.stage.generateur_facture.entities.Supplier;

import java.util.List;
import java.util.Optional;

public interface ISupplierService {
    List<Supplier> getAllSuppliers();

    Optional<Supplier> getSupplierById(Long id) ;

    Supplier createSupplier(Supplier supplier);

    Supplier updateSupplier(Long id, Supplier supplier);

    void deleteSupplier(Long id) ;
    List<Supplier> searchSupplier(String name);

}
