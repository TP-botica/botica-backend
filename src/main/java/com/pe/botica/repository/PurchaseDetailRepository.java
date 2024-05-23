package com.pe.botica.repository;

import com.pe.botica.model.Purchase;
import com.pe.botica.model.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail, UUID> {
}
