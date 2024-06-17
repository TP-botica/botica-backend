package com.pe.botica.controller;

import com.pe.botica.dto.DrugstoreLocationsDTO;
import com.pe.botica.dto.DrugstoreServiceDTO;
import com.pe.botica.dto.DrugstoreServiceEditableDTO;
import com.pe.botica.dto.DrugstoreServiceViewDTO;
import com.pe.botica.model.*;
import com.pe.botica.model.compoundId.DrugstoreServiceId;
import com.pe.botica.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/drugstoreService")
public class DrugstoreServiceController {
    @Autowired
    private DrugstoreServiceService drugstoreServiceService;
    @Autowired
    private UserService userService;
    @Autowired
    private ServiceService serviceService;

    @GetMapping("/all")
    public ResponseEntity<List<DrugstoreService>> getAllDrugstoreServices(){
        List<DrugstoreService> drugstoreServices = drugstoreServiceService.findAll();
        return new ResponseEntity<>(drugstoreServices, HttpStatus.OK);
    }
    @GetMapping("/locations/{serviceId}")
    public ResponseEntity<List<DrugstoreLocationsDTO>> getAllDrugstoreLocations(
            @PathVariable("serviceId") UUID serviceId
    ){
        List<DrugstoreLocationsDTO> drugstoreLocations= drugstoreServiceService.findDrugstoreLocations(serviceId);
        return new ResponseEntity<>(drugstoreLocations, HttpStatus.OK);
    }
    @GetMapping("/searchById/{drugstoreId}/{serviceId}")
    public ResponseEntity<DrugstoreServiceViewDTO> findById(
            @PathVariable("drugstoreId") UUID drugstoreId,
            @PathVariable("serviceId") UUID serviceId
            ){
        var response = drugstoreServiceService.getDrugstoreServiceByIds(drugstoreId, serviceId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<DrugstoreService> addDrugstoreService( @RequestBody DrugstoreServiceDTO drugstoreServiceDTO ){
        DrugstoreServiceId id = new DrugstoreServiceId(drugstoreServiceDTO.getServiceId(), drugstoreServiceDTO.getDrugstoreId());
        Optional<DrugstoreService> existingDrugstoreService = drugstoreServiceService
                .findById(id);

        if (existingDrugstoreService.isPresent()) {
            throw new RuntimeException("A service with the same drugstoreId and serviceId already exists.");
        }

        Optional<User> drugstore = userService.findById(drugstoreServiceDTO.getDrugstoreId());
        Optional<Service> service = serviceService.findById(drugstoreServiceDTO.getServiceId());
        DrugstoreService drugstoreService = new DrugstoreService();
        drugstoreService.setPrice(drugstoreServiceDTO.getPrice());
        drugstore.ifPresent(drugstoreService::setUser);
        service.ifPresent(drugstoreService::setService);

        DrugstoreService newDrugstoreService = drugstoreServiceService.save(drugstoreService);
        return new ResponseEntity<>(newDrugstoreService, HttpStatus.CREATED);
    }
    @PutMapping("/edit/{drugstoreId}/{serviceId}")
    public ResponseEntity<DrugstoreService> updateDrugstoreProduct(
            @PathVariable("drugstoreId") UUID drugstoreId,
            @PathVariable("serviceId") UUID serviceId,
            @RequestBody DrugstoreServiceEditableDTO drugstoreService
    ){
        DrugstoreServiceId id = new DrugstoreServiceId(serviceId, drugstoreId);
        DrugstoreService drugstoreServiceUpdate = drugstoreServiceService.findById(id)
                .orElseThrow(()->new RuntimeException("drugstore service not found with id: " + id));

        drugstoreServiceUpdate.setPrice(drugstoreService.getPrice());

        return new ResponseEntity<>(drugstoreServiceService.save(drugstoreServiceUpdate), HttpStatus.OK);
    }
    @DeleteMapping("/deleteById/{drugstoreId}/{serviceId}")
    public ResponseEntity<HttpStatus> deleteDrugstoreService(
            @PathVariable("drugstoreId") UUID drugstoreId,
            @PathVariable("serviceId") UUID serviceId
    ){
        DrugstoreServiceId id = new DrugstoreServiceId(serviceId, drugstoreId);
        drugstoreServiceService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
