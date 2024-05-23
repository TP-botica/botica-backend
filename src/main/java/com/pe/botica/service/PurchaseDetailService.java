package com.pe.botica.service;

import com.pe.botica.model.PurchaseDetail;
import com.pe.botica.repository.PurchaseDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PurchaseDetailService {
    @Autowired
    private PurchaseDetailRepository purchaseDetailRepository;
    public List<PurchaseDetail> findAll() { return purchaseDetailRepository.findAll();}
    public Optional<PurchaseDetail> findById(UUID id ) { return purchaseDetailRepository.findById(id);}
    public PurchaseDetail save( PurchaseDetail purchaseDetail ){ return purchaseDetailRepository.save(purchaseDetail); }
    public void deleteById( UUID id ){ purchaseDetailRepository.deleteById(id);}
}
