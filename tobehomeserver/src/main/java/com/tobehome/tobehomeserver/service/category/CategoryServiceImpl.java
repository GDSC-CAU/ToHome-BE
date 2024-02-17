package com.tobehome.tobehomeserver.service.category;

import com.tobehome.tobehomeserver.domain.entity.category.FurnitureCategory;
import com.tobehome.tobehomeserver.domain.entity.category.MaterialCategory;
import com.tobehome.tobehomeserver.dto.request.CategoryDTO;
import com.tobehome.tobehomeserver.repository.category.FurnitureCategoryJpaRepository;
import com.tobehome.tobehomeserver.repository.category.MaterialCategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final MaterialCategoryJpaRepository materialCategoryRepository;
    private final FurnitureCategoryJpaRepository furnitureCategoryRepository;

    @Override
    public List<CategoryDTO> getAllMaterialCategories() {
        List<MaterialCategory> materialCategories = materialCategoryRepository.findAll();
        return materialCategories.stream().map(CategoryDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> getAllFurnitureCategories() {
        List<FurnitureCategory> furnitureCategories = furnitureCategoryRepository.findAll();
        return furnitureCategories.stream().map(CategoryDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO addMaterialCategory(CategoryDTO categoryDTO) {
        MaterialCategory materialCategory = new MaterialCategory();
        materialCategory.setName(categoryDTO.getName());
        MaterialCategory savedCategory = materialCategoryRepository.save(materialCategory);
        categoryDTO.setId(savedCategory.getId());
        return categoryDTO;
    }

    @Override
    public CategoryDTO addFurnitureCategory(CategoryDTO categoryDTO) {
        FurnitureCategory furnitureCategory = new FurnitureCategory();
        furnitureCategory.setName(categoryDTO.getName());
        FurnitureCategory savedCategory = furnitureCategoryRepository.save(furnitureCategory);
        categoryDTO.setId(savedCategory.getId());
        return categoryDTO;
    }
}

