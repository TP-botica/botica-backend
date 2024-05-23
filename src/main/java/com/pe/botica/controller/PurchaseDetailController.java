package com.pe.botica.controller;

import com.pe.botica.dto.PurchaseDTO;
import com.pe.botica.dto.PurchaseDetailDTO;
import com.pe.botica.model.*;
import com.pe.botica.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/purchaseDetail")
public class PurchaseDetailController {
    @Autowired
    private PurchaseDetailService purchaseDetailService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/all")
    public ResponseEntity<List<PurchaseDetail>> getAllPurchaseDetails(){
        List<PurchaseDetail> purchases = purchaseDetailService.findAll();
        return new ResponseEntity<>(purchases, HttpStatus.OK);
    }
    @GetMapping("/searchById/{id}")
    public ResponseEntity<Optional<PurchaseDetail>> findById(
            @PathVariable("id") UUID id
            ){
        var response = purchaseDetailService.findById(id);
        if(response.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<Object> addPurchaseDetail( @RequestBody PurchaseDetailDTO purchaseDetailDTO ){
        Optional<Product> product = productService.findById(purchaseDetailDTO.getProductId());
        Optional<Service> service = serviceService.findById(purchaseDetailDTO.getServiceId());
        Optional<Purchase> purchase = purchaseService.findById(purchaseDetailDTO.getPurchaseId());

        if ((product.isEmpty() && service.isEmpty()) || purchase.isEmpty()) {
            return new ResponseEntity<>("Product and Service not found", HttpStatus.BAD_REQUEST);
        }

        PurchaseDetail purchaseDetail = new PurchaseDetail();
        purchaseDetail.setQuantity(purchaseDetailDTO.getQuantity());
        purchaseDetail.setPrice(purchaseDetailDTO.getPrice());
        purchaseDetail.setDiscount(purchaseDetailDTO.getDiscount());
        product.ifPresent(purchaseDetail::setProduct);
        service.ifPresent(purchaseDetail::setService);
        purchase.ifPresent(purchaseDetail::setPurchase);

        PurchaseDetail newPurchaseDetail = purchaseDetailService.save(purchaseDetail);
        return new ResponseEntity<>(newPurchaseDetail, HttpStatus.CREATED);
    }
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<HttpStatus> deletePurchaseDetail( @PathVariable("id") UUID id){
        purchaseDetailService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
