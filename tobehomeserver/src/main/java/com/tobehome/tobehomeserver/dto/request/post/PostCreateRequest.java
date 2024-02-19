package com.tobehome.tobehomeserver.dto.request.post;

import com.tobehome.tobehomeserver.domain.entity.Post;
import com.tobehome.tobehomeserver.dto.request.CategoryDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateRequest {
    private Long user_id;
    private String title;
    private String shortDescription;
    private String content;
    private String type;
    private Long materialCategory;
    private Long furnitureCategory;
    private String imageUrl;
    private String imageUrl2;
    private String imageUrl3;
    private List<Post.Rel> rel;



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