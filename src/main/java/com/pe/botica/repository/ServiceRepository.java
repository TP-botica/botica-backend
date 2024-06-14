package com.pe.botica.repository;

import com.pe.botica.dto.MyServicesViewDTO;
import com.pe.botica.dto.ServiceOptionDTO;
import com.pe.botica.dto.ServiceViewDTO;
import com.pe.botica.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ServiceRepository extends JpaRepository<Service, UUID> {
    @Query(value = """
             SELECT new com.pe.botica.dto.ServiceViewDTO(
             s.name,
             s.description,
             ds.price,
             u.name,
             s.imageUrl
            ) from Service s
              inner join DrugstoreService ds
              on s.id = ds.service.id
              inner join User u
              on ds.user.id = u.id
            """
    )
    public List<ServiceViewDTO> getAllServices();

    @Query(value = """
             SELECT new com.pe.botica.dto.ServiceOptionDTO(
             s.id,
             s.name
            ) from Service s
            """
    )
    List<ServiceOptionDTO> getAllServiceOptions();
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
