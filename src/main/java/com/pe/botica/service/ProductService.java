package com.pe.botica.service;

import com.pe.botica.dto.ProductViewDTO;
import com.pe.botica.model.Product;
import com.pe.botica.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public List<Product> findAll() { return productRepository.findAll();}
    public Optional<Product> findById(UUID id ) { return productRepository.findById(id);}
    public Product save( Product product ){ return productRepository.save(product); }
    public void deleteById( UUID id ){ productRepository.deleteById(id);}
    public List<ProductViewDTO> findAllProducts(){ return productRepository.getAllProducts();}
}
