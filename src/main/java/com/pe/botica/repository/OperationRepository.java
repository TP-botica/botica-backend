package com.pe.botica.repository;

import com.pe.botica.model.security.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OperationRepository extends JpaRepository<Operation, UUID> {

    @Query("SELECT o FROM Operation o WHERE o.permitAll = true")
    public List<Operation> findByPublicAccess();
}
