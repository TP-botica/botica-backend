package com.pe.botica.controller;

import com.pe.botica.dto.ProductDTO;
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

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/searchById/{id}")
    public ResponseEntity<Optional<Product>> findById(
            @PathVariable("id") UUID id
            ){
        var response = productService.findById(id);
        if(response.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
