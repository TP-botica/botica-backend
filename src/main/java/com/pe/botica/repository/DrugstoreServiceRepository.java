package com.pe.botica.repository;

import com.pe.botica.model.DrugstoreService;
import com.pe.botica.model.compoundId.DrugstoreServiceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugstoreServiceRepository extends JpaRepository<DrugstoreService, DrugstoreServiceId> {
}
