package com.tobehome.tobehomeserver.repository;

import com.tobehome.tobehomeserver.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostJpaRepository extends JpaRepository<Post, Long> {
    List<Post> findByFurnitureCategory(Long furnitureCategoryId);

    List<Post> findByMaterialCategory(Long materialCategoryId);

    List<Post> findByUserId(Long userId);
}
