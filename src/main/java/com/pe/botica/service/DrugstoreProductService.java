package com.pe.botica.service;

import com.pe.botica.dto.DrugstoreLocationsDTO;
import com.pe.botica.dto.DrugstoreProductViewDTO;
import com.pe.botica.model.DrugstoreProduct;
import com.pe.botica.model.compoundId.DrugstoreProductId;
import com.pe.botica.repository.DrugstoreProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DrugstoreProductService {
    @Autowired
    private DrugstoreProductRepository drugstoreProductRepository;
    public List<DrugstoreProduct> findAll() { return drugstoreProductRepository.findAll();}
    public Optional<DrugstoreProduct> findById(DrugstoreProductId id ) { return drugstoreProductRepository.findById(id);}
    public DrugstoreProductViewDTO getDetailsById(UUID drugstoreId,UUID productId) { return drugstoreProductRepository.getDrugstoreProductByIds(drugstoreId, productId);}
    public DrugstoreProduct save( DrugstoreProduct drugstoreProduct ){ return drugstoreProductRepository.save(drugstoreProduct); }
    public List<DrugstoreLocationsDTO> findDrugstoreLocations(UUID productId){ return drugstoreProductRepository.getLocationsByProduct(productId); }
    public void deleteById( DrugstoreProductId id ){ drugstoreProductRepository.deleteById(id);}
}
