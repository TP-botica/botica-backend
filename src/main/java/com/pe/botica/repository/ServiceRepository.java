package com.pe.botica.repository;

import com.pe.botica.dto.MyServicesViewDTO;
import com.pe.botica.dto.OptionDTO;
import com.pe.botica.dto.ProductServiceViewDTO;
import com.pe.botica.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
            """
    )
    List<OptionDTO> getAllServiceOptions();
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

}
