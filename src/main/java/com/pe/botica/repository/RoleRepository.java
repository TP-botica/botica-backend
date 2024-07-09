package com.pe.botica.repository;

import com.pe.botica.dto.RoleDTO;
import com.pe.botica.model.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    @Query(value = """
             SELECT new com.pe.botica.dto.RoleDTO(
                 r.id,
                 r.name
             )
             FROM Role r
             WHERE r.name != 'admin'
            """
    )
    public List<RoleDTO> getRoles();
}
