package com.pe.botica.service;

import com.pe.botica.dto.ProductViewDTO;
import com.pe.botica.dto.ServiceViewDTO;
import com.pe.botica.model.Product;
import com.pe.botica.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;
    public List<com.pe.botica.model.Service> findAll() { return serviceRepository.findAll();}
    public Optional<com.pe.botica.model.Service> findById(UUID id ) { return serviceRepository.findById(id);}
    public com.pe.botica.model.Service save( com.pe.botica.model.Service service ){ return serviceRepository.save(service); }
    public void deleteById( UUID id ){ serviceRepository.deleteById(id);}
    public List<ServiceViewDTO> findAllServices(){ return serviceRepository.getAllServices();}

}
