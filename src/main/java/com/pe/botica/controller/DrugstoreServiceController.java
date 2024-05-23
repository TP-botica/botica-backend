package com.pe.botica.controller;

import com.pe.botica.dto.DrugstoreServiceDTO;
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
    @GetMapping("/searchById/{drugstoreId}/{serviceId}")
    public ResponseEntity<Optional<DrugstoreService>> findById(
            @PathVariable("drugstoreId") UUID drugstoreId,
            @PathVariable("serviceId") UUID serviceId
            ){
        DrugstoreServiceId id = new DrugstoreServiceId(serviceId, drugstoreId);
        var response = drugstoreServiceService.findById(id);
        if(response.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<DrugstoreService> addDrugstoreService( @RequestBody DrugstoreServiceDTO drugstoreServiceDTO ){
        Optional<User> drugstore = userService.findById(drugstoreServiceDTO.getDrugstoreId());
        Optional<Service> service = serviceService.findById(drugstoreServiceDTO.getServiceId());
        DrugstoreService drugstoreService = new DrugstoreService();
        drugstoreService.setPrice(drugstoreServiceDTO.getPrice());
        drugstore.ifPresent(drugstoreService::setUser);
        service.ifPresent(drugstoreService::setService);

        DrugstoreService newDrugstoreService = drugstoreServiceService.save(drugstoreService);
        return new ResponseEntity<>(newDrugstoreService, HttpStatus.CREATED);
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
