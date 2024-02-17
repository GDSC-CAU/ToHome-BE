package com.tobehome.tobehomeserver.repository.category;

import com.tobehome.tobehomeserver.domain.entity.category.MaterialCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialCategoryJpaRepository extends JpaRepository<MaterialCategory, Long> {
}
