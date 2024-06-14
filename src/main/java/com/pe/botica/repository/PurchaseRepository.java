package com.pe.botica.repository;

import com.pe.botica.dto.ProductOptionDTO;
import com.pe.botica.dto.PurchaseViewDTO;
import com.pe.botica.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, UUID> {
    @Query(value = """
         SELECT new com.pe.botica.dto.PurchaseViewDTO(
         p.id,
         u.name,
         p.date,
         p.total
        )
        FROM Purchase p
        INNER JOIN User u ON u.id = p.customer.id
        WHERE p.drugstore.id = :drugstoreId
        """)
    public List<PurchaseViewDTO> getAllMySales(UUID drugstoreId);

    @Query(value = """
         SELECT new com.pe.botica.dto.PurchaseViewDTO(
         p.id,
         u.name,
         p.date,
         p.total
        )
        FROM Purchase p
        INNER JOIN User u ON u.id = p.drugstore.id
        WHERE p.customer.id = :customerId
        """)
    List<PurchaseViewDTO> getAllMyPurchases(UUID customerId);
}
