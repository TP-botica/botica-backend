package com.pe.botica.service;

import com.pe.botica.dto.PurchaseViewDTO;
import com.pe.botica.model.Purchase;
import com.pe.botica.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;
    public List<Purchase> findAll() { return purchaseRepository.findAll();}
    public Optional<Purchase> findById(UUID id ) { return purchaseRepository.findById(id);}
    public Purchase save( Purchase purchase ){ return purchaseRepository.save(purchase); }
    public void deleteById( UUID id ){ purchaseRepository.deleteById(id);}
    public List<PurchaseViewDTO> findAllMySales(UUID drugstoreId) {
        return purchaseRepository.getAllMySales(drugstoreId);
    }
    public List<PurchaseViewDTO> findAllMyPurchases(UUID customerId) {
        return purchaseRepository.getAllMyPurchases(customerId);
    }
}
