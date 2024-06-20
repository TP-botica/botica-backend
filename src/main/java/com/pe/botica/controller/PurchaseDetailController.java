package com.pe.botica.controller;

import com.pe.botica.dto.PurchaseDetailDTO;
import com.pe.botica.dto.PurchaseDetailViewDTO;
import com.pe.botica.model.*;
import com.pe.botica.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@CrossOrigin(origins = {"http://localhost:4200", "http://angular-bucket-app-2.s3-website-us-east-1.amazonaws.com"})
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
    @GetMapping("/allByPurchase/{purchaseId}")
    public ResponseEntity<List<PurchaseDetailViewDTO>> getAllPurchaseDetailsByPurchase(
            @PathVariable("purchaseId") UUID purchaseId
    ){
        List<PurchaseDetailViewDTO> purchases = purchaseDetailService.findAllPurchaseDetailsByPurchase(purchaseId);
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
    public ResponseEntity<Object> addPurchaseDetail( @RequestBody PurchaseDetailDTO purchaseDetailDTO ) {
        Optional<Purchase> purchase = purchaseService.findById(purchaseDetailDTO.getPurchaseId());
        if (purchase.isEmpty()) {
            return new ResponseEntity<>("Purchase not found", HttpStatus.BAD_REQUEST);
        }

        boolean hasProduct = purchaseDetailDTO.getProductId() != null;
        boolean hasService = purchaseDetailDTO.getServiceId() != null;

        if (hasProduct == hasService) {
            return new ResponseEntity<>("Exactly one of productId or serviceId must be provided, not both", HttpStatus.BAD_REQUEST);
        }

        PurchaseDetail purchaseDetail = new PurchaseDetail();
        purchaseDetail.setQuantity(purchaseDetailDTO.getQuantity());
        purchaseDetail.setPrice(purchaseDetailDTO.getPrice());
        purchaseDetail.setDiscount(purchaseDetailDTO.getDiscount());
        purchase.ifPresent(purchaseDetail::setPurchase);
        if (hasProduct) {
            productService.findById(purchaseDetailDTO.getProductId())
                    .ifPresentOrElse(purchaseDetail::setProduct,
                            () -> { throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found"); });
        } else {
            serviceService.findById(purchaseDetailDTO.getServiceId())
                    .ifPresentOrElse(purchaseDetail::setService,
                            () -> { throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Service not found"); });
        }
        PurchaseDetail newPurchaseDetail = purchaseDetailService.save(purchaseDetail);
        return new ResponseEntity<>(newPurchaseDetail, HttpStatus.CREATED);
    }
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<HttpStatus> deletePurchaseDetail( @PathVariable("id") UUID id){
        purchaseDetailService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
