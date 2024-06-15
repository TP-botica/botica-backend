package com.pe.botica.service;

import com.pe.botica.dto.OptionDTO;
import com.pe.botica.model.Category;
import com.pe.botica.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public List<Category> findAll() { return categoryRepository.findAll();}
    public Optional<Category> findById(UUID id ) { return categoryRepository.findById(id);}
    public Category save( Category category ){ return categoryRepository.save(category); }
    public void deleteById( UUID id ){ categoryRepository.deleteById(id);}

    public List<OptionDTO> findAllProductOptions() {
        return categoryRepository.getAllCategoryProductOptions();
    }
    public List<OptionDTO> findAllServiceOptions() {
        return categoryRepository.getAllCategoryServiceOptions();
    }
}
