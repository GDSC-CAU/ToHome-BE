package com.tobehome.tobehomeserver.dto.request.post;

import com.tobehome.tobehomeserver.dto.request.CategoryDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostUpdateRequest {
    private String title;
    private String shortDescription;
    private String content;
    private Long materialCategory;
    private Long furnitureCategory;
    private String imageUrl;
    private String imageUrl2;
    private String imageUrl3;

    public CategoryDTO toMaterialCategoryDTO() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(materialCategory);
        return categoryDTO;
    }

    public CategoryDTO toFurnitureCategoryDTO() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(furnitureCategory);
        return categoryDTO;
    }
}