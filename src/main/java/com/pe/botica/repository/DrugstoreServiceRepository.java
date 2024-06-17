package com.pe.botica.repository;

import com.pe.botica.dto.DrugstoreLocationsDTO;
import com.pe.botica.dto.DrugstoreProductViewDTO;
import com.pe.botica.dto.DrugstoreServiceViewDTO;
import com.pe.botica.model.DrugstoreService;
import com.pe.botica.model.compoundId.DrugstoreServiceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DrugstoreServiceRepository extends JpaRepository<DrugstoreService, DrugstoreServiceId> {
    @Query(value = """
    SELECT new com.pe.botica.dto.DrugstoreLocationsDTO(
        ds.user.id,
        ds.user.name,
        ds.user.latitude,
        ds.user.longitude
    )
    FROM DrugstoreService ds
    WHERE ds.service.id = :serviceId
    """)
    public List<DrugstoreLocationsDTO> getLocationsByService(UUID serviceId);
    @Query(value = """
    SELECT new com.pe.botica.dto.DrugstoreServiceViewDTO(
        ds.user.name,
        ds.service.name,
        ds.price
    )
    FROM DrugstoreService ds
    WHERE ds.service.id = :serviceId AND ds.user.id = :drugstoreId
    """)
    public DrugstoreServiceViewDTO getDrugstoreServiceByIds(UUID drugstoreId, UUID serviceId);
}
