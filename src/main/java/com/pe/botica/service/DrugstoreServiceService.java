package com.pe.botica.service;

import com.pe.botica.dto.DrugstoreLocationsDTO;
import com.pe.botica.dto.DrugstoreServiceViewDTO;
import com.pe.botica.model.DrugstoreService;
import com.pe.botica.model.compoundId.DrugstoreServiceId;
import com.pe.botica.repository.DrugstoreServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DrugstoreServiceService {
    @Autowired
    private DrugstoreServiceRepository drugstoreServiceRepository;
    public List<DrugstoreService> findAll() { return drugstoreServiceRepository.findAll();}
    public Optional<DrugstoreService> findById(DrugstoreServiceId id ) { return drugstoreServiceRepository.findById(id);}
    public DrugstoreService save( DrugstoreService drugstoreService ){ return drugstoreServiceRepository.save(drugstoreService); }
    public DrugstoreServiceViewDTO getDrugstoreServiceByIds(UUID drugstoreId, UUID serviceId ){ return drugstoreServiceRepository.getDrugstoreServiceByIds(drugstoreId, serviceId); }
    public List<DrugstoreLocationsDTO> findDrugstoreLocations(UUID serviceId){ return drugstoreServiceRepository.getLocationsByService(serviceId); }
    public void deleteById( DrugstoreServiceId id ){ drugstoreServiceRepository.deleteById(id);}
}
