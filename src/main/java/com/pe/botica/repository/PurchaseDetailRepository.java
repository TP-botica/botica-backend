package com.pe.botica.repository;

import com.pe.botica.dto.PurchaseDetailViewDTO;
import com.pe.botica.model.Purchase;
import com.pe.botica.model.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail, UUID> {
    @Query(value= """
         SELECT new com.pe.botica.dto.PurchaseDetailViewDTO(
         COALESCE(p.name, s.name),
         pd.quantity,
         pd.price,
         pd.discount
        )
        FROM PurchaseDetail pd
        LEFT JOIN pd.product p
        LEFT JOIN pd.service s
        WHERE pd.purchase.id = :purchaseId
            """)
    List<PurchaseDetailViewDTO> findAllByPurchase(UUID purchaseId);
}
