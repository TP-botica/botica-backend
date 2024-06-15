package com.pe.botica.repository;

import com.pe.botica.dto.OptionDTO;
import com.pe.botica.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Query(value = """
         SELECT new com.pe.botica.dto.OptionDTO(
         c.id,
         c.name
        )
        FROM Category c
        WHERE c.type = 'p'
        """)
    List<OptionDTO> getAllCategoryProductOptions();

    @Query(value = """
         SELECT new com.pe.botica.dto.OptionDTO(
         c.id,
         c.name
        )
        FROM Category c
        WHERE c.type = 's'
        """)
    List<OptionDTO> getAllCategoryServiceOptions();
}
