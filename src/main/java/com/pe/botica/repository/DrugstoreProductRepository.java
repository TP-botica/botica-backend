package com.pe.botica.repository;

import com.pe.botica.model.DrugstoreProduct;
import com.pe.botica.model.compoundId.DrugstoreProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface DrugstoreProductRepository extends JpaRepository<DrugstoreProduct, DrugstoreProductId> {
}
