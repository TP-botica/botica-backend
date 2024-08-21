package com.pe.botica.service;

import com.pe.botica.dto.MyProductsViewDTO;
import com.pe.botica.dto.OptionDTO;
import com.pe.botica.dto.ProductDetailDTO;
import com.pe.botica.dto.ProductServiceViewDTO;
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
    public List<ProductServiceViewDTO> findAll() { return productRepository.getAllProducts();}
    public List<ProductServiceViewDTO> findAllByCategory(UUID categoryId) { return productRepository.getProductsByCategory(categoryId);}
    public ProductDetailDTO getProductDetailsById(UUID id ) { return productRepository.getProductDetailsById(id);}
    public Optional<Product> findById(UUID id ) { return productRepository.findById(id);}
    public Product save( Product product ){ return productRepository.save(product); }
    public void deleteById( UUID id ){ productRepository.deleteById(id);}
    public List<OptionDTO> findAllProductOptions( UUID drugstoreId ){ return productRepository.getAllProductOptions(drugstoreId);}
    public List<MyProductsViewDTO> findAllMyProducts(UUID drugstoreId){ return productRepository.getAllMyProducts(drugstoreId);}

}
