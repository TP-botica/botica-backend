package com.pe.botica.controller;

import com.pe.botica.dto.*;
import com.pe.botica.model.Category;
import com.pe.botica.model.Product;
import com.pe.botica.service.CategoryService;
import com.pe.botica.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@CrossOrigin(origins = {"http://localhost:4200", "https://dfofszpxxxtk5.cloudfront.net", "https://medifinderperu.com"})
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/all")
    public ResponseEntity<List<ProductServiceViewDTO>> getAllProducts(){
        List<ProductServiceViewDTO> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/allByCategory/{categoryId}")
    public ResponseEntity<List<ProductServiceViewDTO>> getAllProductsByCategory(
            @PathVariable("categoryId") UUID categoryId
    ){
        List<ProductServiceViewDTO> products = productService.findAllByCategory(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/all/options/{drugstoreId}")
    public ResponseEntity<List<OptionDTO>> getAllProductsOptions(
            @PathVariable("drugstoreId") UUID drugstoreId
    ){
        List<OptionDTO> products = productService.findAllProductOptions(drugstoreId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/allMyProducts/{drugstoreId}")
    public ResponseEntity<List<MyProductsViewDTO>> getAllMyProducts(
            @PathVariable("drugstoreId") UUID drugstoreId
    ){
        List<MyProductsViewDTO> products = productService.findAllMyProducts(drugstoreId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/searchById/{id}")
    public ResponseEntity<ProductDetailDTO> findById(
            @PathVariable("id") UUID id
            ){
        var response = productService.getProductDetailsById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<Product> addProduct( @RequestBody ProductDTO productDTO ){
        Optional<Category> category = categoryService.findById(productDTO.getCategoryId());
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setState(productDTO.getState());
        product.setPrescriptionRequired(productDTO.getPrescriptionRequired());
        product.setImageUrl(productDTO.getImageUrl());
        category.ifPresent(product::setCategory);

        Product newProduct = productService.save(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<HttpStatus> deleteProduct( @PathVariable("id") UUID id){
        productService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
