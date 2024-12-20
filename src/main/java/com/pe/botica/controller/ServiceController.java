package com.pe.botica.controller;

import com.pe.botica.dto.*;
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
@CrossOrigin(origins = {"http://localhost:4200", "https://dfofszpxxxtk5.cloudfront.net", "https://medifinderperu.com"})
@RestController
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/all")
    public ResponseEntity<List<ProductServiceViewDTO>> getAllServices(){
        List<ProductServiceViewDTO> services = serviceService.findAll();
        return new ResponseEntity<>(services, HttpStatus.OK);
    }
    @GetMapping("/allByCategory/{categoryId}")
    public ResponseEntity<List<ProductServiceViewDTO>> getAllServicesByCategory(
            @PathVariable("categoryId") UUID categoryId
    ){
        List<ProductServiceViewDTO> services = serviceService.findAllByCategory(categoryId);
        return new ResponseEntity<>(services, HttpStatus.OK);
    }
    @GetMapping("/searchById/{id}")
    public ResponseEntity<ServiceDetailDTO> findById(
            @PathVariable("id") UUID id
            ){
        var response = serviceService.getDetailsById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/allMyServices/{drugstoreId}")
    public ResponseEntity<List<MyServicesViewDTO>> getAllMyServices(
            @PathVariable("drugstoreId") UUID drugstoreId
    ){
        List<MyServicesViewDTO> services = serviceService.findAllMyServices(drugstoreId);
        return new ResponseEntity<>(services, HttpStatus.OK);
    }
    @GetMapping("/all/options/{drugstoreId}")
    public ResponseEntity<List<OptionDTO>> getAllProductsOptions(@PathVariable("drugstoreId") UUID drugstoreId){
        List<OptionDTO> services = serviceService.findAllServiceOptions(drugstoreId);
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
