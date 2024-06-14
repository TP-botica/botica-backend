package com.pe.botica.controller;

import com.pe.botica.dto.PurchaseRegisterDTO;
import com.pe.botica.dto.PurchaseViewDTO;
import com.pe.botica.model.Purchase;
import com.pe.botica.model.PurchaseDetail;
import com.pe.botica.model.User;
import com.pe.botica.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private UserService userService;
    @Autowired
    private PurchaseDetailService purchaseDetailService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ServiceService serviceService;

    @GetMapping("/all")
    public ResponseEntity<List<Purchase>> getAllPurchases(){
        List<Purchase> purchases = purchaseService.findAll();
        return new ResponseEntity<>(purchases, HttpStatus.OK);
    }
    @GetMapping("/allMySales/{drugstoreId}")
    public ResponseEntity<List<PurchaseViewDTO>> getAllMySales(
            @PathVariable("drugstoreId") UUID drugstoreId
    ){
        List<PurchaseViewDTO> purchases = purchaseService.findAllMySales(drugstoreId);
        return new ResponseEntity<>(purchases, HttpStatus.OK);
    }
    @GetMapping("/allMyPurchases/{customerId}")
    public ResponseEntity<List<PurchaseViewDTO>> getAllMyPurchases(
            @PathVariable("customerId") UUID customerId
    ){
        List<PurchaseViewDTO> purchases = purchaseService.findAllMyPurchases(customerId);
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
    public ResponseEntity<Object> addPurchase(@RequestBody PurchaseRegisterDTO purchaseRegisterDTO) {
        Optional<User> drugstore = userService.findById(purchaseRegisterDTO.getDrugstoreId());
        Optional<User> customer = userService.findById(purchaseRegisterDTO.getCustomerId());
        final double[] total = {0.0};
        if (drugstore.isEmpty() || customer.isEmpty()) {
            return new ResponseEntity<>("Drugstore or customer not found", HttpStatus.BAD_REQUEST);
        }

        Purchase purchase = new Purchase();
        purchase.setDate(new Date());

        drugstore.ifPresent(purchase::setDrugstore);
        customer.ifPresent(purchase::setCustomer);

        Purchase newPurchase = purchaseService.save(purchase);

        if (!purchaseRegisterDTO.getPurchaseDetails().isEmpty()) {
            purchaseRegisterDTO.getPurchaseDetails().forEach(purchaseDetailDTO -> {
                boolean hasProduct = purchaseDetailDTO.getProductId() != null;
                boolean hasService = purchaseDetailDTO.getServiceId() != null;

                PurchaseDetail purchaseDetail = new PurchaseDetail();
                purchaseDetail.setQuantity(purchaseDetailDTO.getQuantity());
                purchaseDetail.setPrice(purchaseDetailDTO.getPrice());
                purchaseDetail.setDiscount(purchaseDetailDTO.getDiscount());
                purchaseDetail.setPurchase(newPurchase);

                if (hasProduct) {
                    productService.findById(purchaseDetailDTO.getProductId())
                            .ifPresentOrElse(purchaseDetail::setProduct, () -> {
                                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found");
                            });
                } else if (hasService) {
                    serviceService.findById(purchaseDetailDTO.getServiceId())
                            .ifPresentOrElse(purchaseDetail::setService, () -> {
                                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Service not found");
                            });
                } else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product or Service ID must be provided");
                }
                double subtotal = purchaseDetailDTO.getQuantity() * purchaseDetailDTO.getPrice();
                double discount = purchaseDetailDTO.getDiscount() != null ? purchaseDetailDTO.getDiscount() : 0.0;
                total[0] += subtotal - discount;
                purchaseDetailService.save(purchaseDetail);
            });
        }
        purchase.setTotal((float) total[0]);
        purchaseService.save(purchase);

        return new ResponseEntity<>(newPurchase, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<HttpStatus> deletePurchase( @PathVariable("id") UUID id){
        purchaseService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
