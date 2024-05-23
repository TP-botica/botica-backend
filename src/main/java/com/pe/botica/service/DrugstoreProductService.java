package com.pe.botica.service;

import com.pe.botica.model.DrugstoreProduct;
import com.pe.botica.model.compoundId.DrugstoreProductId;
import com.pe.botica.repository.DrugstoreProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DrugstoreProductService {
    @Autowired
    private DrugstoreProductRepository drugstoreProductRepository;
    public List<DrugstoreProduct> findAll() { return drugstoreProductRepository.findAll();}
    public Optional<DrugstoreProduct> findById(DrugstoreProductId id ) { return drugstoreProductRepository.findById(id);}
    public DrugstoreProduct save( DrugstoreProduct drugstoreProduct ){ return drugstoreProductRepository.save(drugstoreProduct); }
    public void deleteById( DrugstoreProductId id ){ drugstoreProductRepository.deleteById(id);}
}
