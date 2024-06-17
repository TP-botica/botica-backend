package com.pe.botica.service;

import com.pe.botica.dto.MyServicesViewDTO;
import com.pe.botica.dto.OptionDTO;
import com.pe.botica.dto.ProductServiceViewDTO;
import com.pe.botica.dto.ServiceDetailDTO;
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
    public List<ProductServiceViewDTO> findAll() { return serviceRepository.getAllServices();}
    public List<ProductServiceViewDTO> findAllByCategory(UUID categoryId) { return serviceRepository.getServicesByCategory(categoryId);}
    public Optional<com.pe.botica.model.Service> findById(UUID id ) { return serviceRepository.findById(id);}
    public ServiceDetailDTO getDetailsById(UUID serviceId ) { return serviceRepository.getServiceDetailsById(serviceId);}
    public com.pe.botica.model.Service save( com.pe.botica.model.Service service ){ return serviceRepository.save(service); }
    public void deleteById( UUID id ){ serviceRepository.deleteById(id);}
    public List<OptionDTO> findAllServiceOptions() {
        return serviceRepository.getAllServiceOptions();
    }
    public List<MyServicesViewDTO> findAllMyServices(UUID drugstoreId) {
        return serviceRepository.getAllMyServices(drugstoreId);
    }
}
