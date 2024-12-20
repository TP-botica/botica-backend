package com.pe.botica.repository;

import com.pe.botica.dto.MyProductsViewDTO;
import com.pe.botica.dto.OptionDTO;
import com.pe.botica.dto.ProductDetailDTO;
import com.pe.botica.dto.ProductServiceViewDTO;
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
    SELECT new com.pe.botica.dto.ProductServiceViewDTO(
        p.id,
        p.name,
        p.imageUrl,
        c.name
    )
    FROM Product p
    INNER JOIN p.category c
    """)
    public List<ProductServiceViewDTO> getAllProducts();
    @Query(value = """
    SELECT new com.pe.botica.dto.ProductServiceViewDTO(
        p.id,
        p.name,
        p.imageUrl,
        c.name
    )
    FROM Product p
    INNER JOIN p.category c
    WHERE c.id = :categoryId
    """)
    public List<ProductServiceViewDTO> getProductsByCategory(UUID categoryId);

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
    SELECT new com.pe.botica.dto.OptionDTO(
        p.id,
        p.name
    )
    FROM Product p
    WHERE p.id NOT IN (
        SELECT dp.product.id
        FROM DrugstoreProduct dp
        WHERE dp.user.id = :drugstoreId
    )
    """)
    public List<OptionDTO> getAllProductOptions(@Param("drugstoreId") UUID drugstoreId);

    @Query(value = """
        SELECT new com.pe.botica.dto.ProductDetailDTO(
         p.name,
         p.description,
         p.prescriptionRequired,
         p.imageUrl,
         p.category.name
        )
        FROM Product p
        WHERE p.id = :productId
        """)
    public ProductDetailDTO getProductDetailsById(@Param("productId") UUID productId);
}
