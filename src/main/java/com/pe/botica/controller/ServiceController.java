package com.pe.botica.controller;

import com.pe.botica.dto.ProductOptionDTO;
import com.pe.botica.dto.ServiceDTO;
import com.pe.botica.dto.ServiceOptionDTO;
import com.pe.botica.dto.ServiceViewDTO;
import com.pe.botica.model.Category;
import com.pe.botica.service.CategoryService;
import com.pe.botica.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/all")
    public ResponseEntity<List<com.pe.botica.model.Service>> getAllServices(){
        List<com.pe.botica.model.Service> services = serviceService.findAll();
        return new ResponseEntity<>(services, HttpStatus.OK);
    }
    @GetMapping("/searchById/{id}")
    public ResponseEntity<Optional<com.pe.botica.model.Service>> findById(
            @PathVariable("id") UUID id
            ){
        var response = serviceService.findById(id);
        if(response.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/allWithDetails")
    public ResponseEntity<List<ServiceViewDTO>> getAllProductsDetails(){
        List<ServiceViewDTO> services = serviceService.findAllServices();
        return new ResponseEntity<>(services, HttpStatus.OK);
    }
    @GetMapping("/all/options")
    public ResponseEntity<List<ServiceOptionDTO>> getAllProductsOptions(){
        List<ServiceOptionDTO> services = serviceService.findAllServiceOptions();
        return new ResponseEntity<>(services, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<com.pe.botica.model.Service> addService( @RequestBody ServiceDTO serviceDTO ){
        Optional<Category> category = categoryService.findById(serviceDTO.getCategoryId());
        com.pe.botica.model.Service service = new com.pe.botica.model.Service();
        service.setName(serviceDTO.getName());
        service.setDescription(serviceDTO.getDescription());
        service.setState(serviceDTO.getState());
        service.setImageUrl(serviceDTO.getImageUrl());
        category.ifPresent(service::setCategory);

        com.pe.botica.model.Service newProduct = serviceService.save(service);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<HttpStatus> deleteService( @PathVariable("id") UUID id){
        serviceService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
