package com.pe.botica.controller;

import com.pe.botica.dto.DrugstoreLocationsDTO;
import com.pe.botica.dto.DrugstoreProductDTO;
import com.pe.botica.dto.DrugstoreProductEditableDTO;
import com.pe.botica.dto.DrugstoreProductViewDTO;
import com.pe.botica.model.DrugstoreProduct;
import com.pe.botica.model.Product;
import com.pe.botica.model.security.User;
import com.pe.botica.model.compoundId.DrugstoreProductId;
import com.pe.botica.service.DrugstoreProductService;
import com.pe.botica.service.ProductService;
import com.pe.botica.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@CrossOrigin(origins = {"http://localhost:4200", "https://dfofszpxxxtk5.cloudfront.net", "https://medifinderperu.com"})
@RestController
@RequestMapping("/drugstoreProduct")
public class DrugstoreProductController {
    @Autowired
    private DrugstoreProductService drugstoreProductService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<DrugstoreProduct>> getAllDrugstoreProducts(){
        List<DrugstoreProduct> drugstoreProducts = drugstoreProductService.findAll();
        return new ResponseEntity<>(drugstoreProducts, HttpStatus.OK);
    }
    @GetMapping("/locations/{productId}")
    public ResponseEntity<List<DrugstoreLocationsDTO>> getAllDrugstoreLocations(
            @PathVariable("productId") UUID productId
    ){
        List<DrugstoreLocationsDTO> drugstoreLocations= drugstoreProductService.findDrugstoreLocations(productId);
        return new ResponseEntity<>(drugstoreLocations, HttpStatus.OK);
    }
    @GetMapping("/searchById/{drugstoreId}/{productId}")
    public ResponseEntity<DrugstoreProductViewDTO> findById(
            @PathVariable("drugstoreId") UUID drugstoreId,
            @PathVariable("productId") UUID productId
            ){
        var response = drugstoreProductService.getDetailsById(drugstoreId, productId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<DrugstoreProduct> addDrugstoreProduct( @RequestBody DrugstoreProductDTO drugstoreProductDTO ){
        DrugstoreProductId id = new DrugstoreProductId(drugstoreProductDTO.getProductId(), drugstoreProductDTO.getDrugstoreId());
        Optional<DrugstoreProduct> existingDrugstoreProduct = drugstoreProductService
                .findById(id);

        if (existingDrugstoreProduct.isPresent()) {
            throw new RuntimeException("A product with the same drugstoreId and productId already exists.");
        }

        Optional<User> drugstore = userService.findById(drugstoreProductDTO.getDrugstoreId());
        Optional<Product> product = productService.findById(drugstoreProductDTO.getProductId());
        DrugstoreProduct drugstoreProduct = new DrugstoreProduct();
        drugstoreProduct.setPrice(drugstoreProductDTO.getPrice());
        drugstoreProduct.setStock(drugstoreProductDTO.getStock());
        drugstore.ifPresent(drugstoreProduct::setUser);
        product.ifPresent(drugstoreProduct::setProduct);

        DrugstoreProduct newDrugstoreProduct = drugstoreProductService.save(drugstoreProduct);
        return new ResponseEntity<>(newDrugstoreProduct, HttpStatus.CREATED);
    }
    @PutMapping("/edit/{drugstoreId}/{productId}")
    public ResponseEntity<DrugstoreProduct> updateDrugstoreProduct(
            @PathVariable("drugstoreId") UUID drugstoreId,
            @PathVariable("productId") UUID productId,
            @RequestBody DrugstoreProductEditableDTO drugstoreProduct
    ){
        DrugstoreProductId id = new DrugstoreProductId(productId, drugstoreId);
        DrugstoreProduct drugstoreProductUpdate = drugstoreProductService.findById(id)
                .orElseThrow(()->new RuntimeException("drugstore product not found with id: " + id));

        drugstoreProductUpdate.setPrice(drugstoreProduct.getPrice());
        drugstoreProductUpdate.setStock(drugstoreProduct.getStock());

        return new ResponseEntity<>(drugstoreProductService.save(drugstoreProductUpdate), HttpStatus.OK);
    }
    @DeleteMapping("/deleteById/{drugstoreId}/{productId}")
    public ResponseEntity<HttpStatus> deleteDrugstoreProduct(
            @PathVariable("drugstoreId") UUID drugstoreId,
            @PathVariable("productId") UUID productId
    ){
        DrugstoreProductId id = new DrugstoreProductId(productId, drugstoreId);
        drugstoreProductService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
