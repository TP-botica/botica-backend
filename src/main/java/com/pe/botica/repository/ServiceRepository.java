package com.pe.botica.repository;

import com.pe.botica.dto.*;
import com.pe.botica.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ServiceRepository extends JpaRepository<Service, UUID> {
    @Query(value = """
    SELECT new com.pe.botica.dto.ProductServiceViewDTO(
        s.id,
        s.name,
        s.imageUrl,
        c.name
    )
    FROM Service s
    INNER JOIN s.category c
    """)
    public List<ProductServiceViewDTO> getAllServices();
    @Query(value = """
    SELECT new com.pe.botica.dto.ProductServiceViewDTO(
        s.id,
        s.name,
        s.imageUrl,
        c.name
    )
    FROM Service s
    INNER JOIN s.category c
    WHERE c.id = :categoryId
    """)
    public List<ProductServiceViewDTO> getServicesByCategory(UUID categoryId);
    @Query(value = """
             SELECT new com.pe.botica.dto.OptionDTO(
             s.id,
             s.name
            ) from Service s
        WHERE s.id NOT IN (
        SELECT ds.service.id
        FROM DrugstoreService ds
        WHERE ds.user.id = :drugstoreId
    )
    """
    )
    List<OptionDTO> getAllServiceOptions(@Param("drugstoreId") UUID drugstoreId);
    @Query(value = """
     SELECT new com.pe.botica.dto.MyServicesViewDTO(
     s.id,
     s.name,
     s.imageUrl,
     ds.price,
     c.name
    )
    FROM Service s
    INNER JOIN DrugstoreService ds ON s.id = ds.service.id
    INNER JOIN Category c ON c.id = s.category.id
    WHERE ds.user.id = :drugstoreId
    """)
    List<MyServicesViewDTO> getAllMyServices(UUID drugstoreId);

    @Query(value = """
        SELECT new com.pe.botica.dto.ServiceDetailDTO(
         s.name,
         s.description,
         s.imageUrl,
         s.category.name
        )
        FROM Service s
        WHERE s.id = :serviceId
        """)
    public ServiceDetailDTO getServiceDetailsById(@Param("serviceId") UUID serviceId);

}
