package com.pe.botica.repository;

import com.pe.botica.dto.MyProductsViewDTO;
import com.pe.botica.dto.ProductOptionDTO;
import com.pe.botica.dto.ProductViewDTO;
import com.pe.botica.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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


    @Query(value = """
         SELECT new com.pe.botica.dto.MyProductsViewDTO(
         p.id,
         p.name,
         p.imageUrl,
         dp.price,
         c.name,
         dp.stock
        )
        FROM Product p
        INNER JOIN DrugstoreProduct dp ON p.id = dp.product.id
        INNER JOIN Category c ON c.id = p.category.id
        WHERE dp.user.id = :drugstoreId
        """)
    public List<MyProductsViewDTO> getAllMyProducts(@Param("drugstoreId") UUID drugstoreId);

    @Query(value = """
         SELECT new com.pe.botica.dto.ProductOptionDTO(
         p.id,
         p.name
        )
        FROM Product p
        """)
    public List<ProductOptionDTO> getAllProductOptions();

}
