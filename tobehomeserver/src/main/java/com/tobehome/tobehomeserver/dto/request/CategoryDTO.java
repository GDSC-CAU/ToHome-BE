package com.tobehome.tobehomeserver.dto.request;

import com.tobehome.tobehomeserver.domain.entity.category.FurnitureCategory;
import com.tobehome.tobehomeserver.domain.entity.category.MaterialCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;

    public static CategoryDTO fromEntity(MaterialCategory materialCategory) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(materialCategory.getId());
        categoryDTO.setName(materialCategory.getName());
        return categoryDTO;
    }

    public static CategoryDTO fromEntity(FurnitureCategory furnitureCategory) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(furnitureCategory.getId());
        categoryDTO.setName(furnitureCategory.getName());
        return categoryDTO;
    }

}
