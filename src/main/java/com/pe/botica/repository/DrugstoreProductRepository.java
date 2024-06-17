package com.pe.botica.repository;

import com.pe.botica.dto.DrugstoreLocationsDTO;
import com.pe.botica.dto.DrugstoreProductViewDTO;
import com.pe.botica.dto.ProductServiceViewDTO;
import com.pe.botica.model.DrugstoreProduct;
import com.pe.botica.model.compoundId.DrugstoreProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface DrugstoreProductRepository extends JpaRepository<DrugstoreProduct, DrugstoreProductId> {
    @Query(value = """
    SELECT new com.pe.botica.dto.DrugstoreLocationsDTO(
        dp.user.id,
        dp.user.name,
        dp.user.latitude,
        dp.user.longitude
    )
    FROM DrugstoreProduct dp
    WHERE dp.product.id = :productId
    """)
    public List<DrugstoreLocationsDTO> getLocationsByProduct(UUID productId);
    @Query(value = """
    SELECT new com.pe.botica.dto.DrugstoreProductViewDTO(
        dp.user.name,
        dp.product.name,
        dp.price,
        dp.stock
    )
    FROM DrugstoreProduct dp
    WHERE dp.product.id = :productId AND dp.user.id = :drugstoreId
    """)
    public DrugstoreProductViewDTO getDrugstoreProductByIds(UUID drugstoreId,UUID productId);
}
