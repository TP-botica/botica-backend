package com.pe.botica.controller;

import com.pe.botica.dto.OptionDTO;
import com.pe.botica.model.Category;
import com.pe.botica.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @GetMapping("/all/productOptions")
    public ResponseEntity<List<OptionDTO>> getAllCategoryProductOptions(){
        List<OptionDTO> categories = categoryService.findAllProductOptions();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @GetMapping("/all/serviceOptions")
    public ResponseEntity<List<OptionDTO>> getAllCategoryServiceOptions(){
        List<OptionDTO> categories = categoryService.findAllServiceOptions();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @GetMapping("/searchById/{id}")
    public ResponseEntity<Optional<Category>> findById(
            @PathVariable("id") UUID id
            ){
        var response = categoryService.findById(id);
        if(response.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<Category> addCategory( @RequestBody Category category ){
        Category newCategory = categoryService.save(category);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<HttpStatus> deleteCategory( @PathVariable("id") UUID id){
        categoryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
