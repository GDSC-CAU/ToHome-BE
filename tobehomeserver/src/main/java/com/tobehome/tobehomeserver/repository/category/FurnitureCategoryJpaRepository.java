package com.tobehome.tobehomeserver.repository.category;

import com.tobehome.tobehomeserver.domain.entity.category.FurnitureCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FurnitureCategoryJpaRepository extends JpaRepository<FurnitureCategory, Long> {
}

