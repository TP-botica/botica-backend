package com.pe.botica.repository;

import com.pe.botica.dto.ProductViewDTO;
import com.pe.botica.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query(value = """
             SELECT new com.pe.botica.dto.ProductViewDTO(
             p.name,
             p.description,
             dp.price,
             p.prescriptionRequired,
             u.name,
             p.imageUrl,
             dp.stock
            ) from Product p
              inner join DrugstoreProduct dp
              on p.id = dp.product.id
              inner join User u
              on dp.user.id = u.id
            """)
    public List<ProductViewDTO> getAllProducts();
}
