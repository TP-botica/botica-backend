package com.pe.botica.controller;

import com.pe.botica.dto.PurchaseDTO;
import com.pe.botica.model.Purchase;
import com.pe.botica.model.User;
import com.pe.botica.service.PurchaseService;
import com.pe.botica.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<Purchase>> getAllPurchases(){
        List<Purchase> purchases = purchaseService.findAll();
        return new ResponseEntity<>(purchases, HttpStatus.OK);
    }
    @GetMapping("/searchById/{id}")
    public ResponseEntity<Optional<Purchase>> findById(
            @PathVariable("id") UUID id
            ){
        var response = purchaseService.findById(id);
        if(response.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<Object> addService( @RequestBody PurchaseDTO purchaseDTO ){
        Optional<User> drugstore = userService.findById(purchaseDTO.getDrugstoreId());
        Optional<User> customer = userService.findById(purchaseDTO.getCustomerId());

        if (drugstore.isEmpty() || customer.isEmpty()) {
            return new ResponseEntity<>("Drugstore or customer not found", HttpStatus.BAD_REQUEST);
        }

        Purchase purchase = new Purchase();
        purchase.setDate(new Date());
        purchase.setTotal(purchaseDTO.getTotal());
        drugstore.ifPresent(purchase::setDrugstore);
        customer.ifPresent(purchase::setCustomer);

        Purchase newPurchase = purchaseService.save(purchase);
        return new ResponseEntity<>(newPurchase, HttpStatus.CREATED);
    }
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<HttpStatus> deletePurchase( @PathVariable("id") UUID id){
        purchaseService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
