package com.pe.botica.controller;

import com.pe.botica.dto.DrugstoreProductDTO;
import com.pe.botica.model.Category;
import com.pe.botica.model.DrugstoreProduct;
import com.pe.botica.model.Product;
import com.pe.botica.model.User;
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
    @GetMapping("/searchById/{drugstoreId}/{productId}")
    public ResponseEntity<Optional<DrugstoreProduct>> findById(
            @PathVariable("drugstoreId") UUID drugstoreId,
            @PathVariable("productId") UUID productId
            ){
        DrugstoreProductId id = new DrugstoreProductId(productId, drugstoreId);
        var response = drugstoreProductService.findById(id);
        if(response.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<DrugstoreProduct> addDrugstoreProduct( @RequestBody DrugstoreProductDTO drugstoreProductDTO ){
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
