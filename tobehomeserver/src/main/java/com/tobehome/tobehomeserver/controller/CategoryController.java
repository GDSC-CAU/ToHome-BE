package com.tobehome.tobehomeserver.controller;

import com.tobehome.tobehomeserver.dto.request.CategoryDTO;
import com.tobehome.tobehomeserver.dto.request.post.PostDTO;
import com.tobehome.tobehomeserver.service.PostService;
import com.tobehome.tobehomeserver.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final PostService postService;

    @GetMapping("/material")
    public ResponseEntity<List<CategoryDTO>> getAllMaterialCategories() {
        List<CategoryDTO> materialCategories = categoryService.getAllMaterialCategories();
        return new ResponseEntity<>(materialCategories, HttpStatus.OK);
    }

    @GetMapping("/furniture")
    public ResponseEntity<List<CategoryDTO>> getAllFurnitureCategories() {
        List<CategoryDTO> furnitureCategories = categoryService.getAllFurnitureCategories();
        return new ResponseEntity<>(furnitureCategories, HttpStatus.OK);
    }

    @PostMapping("/material")
    public ResponseEntity<CategoryDTO> addMaterialCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO addedCategory = categoryService.addMaterialCategory(categoryDTO);
        return new ResponseEntity<>(addedCategory, HttpStatus.CREATED);
    }

    @PostMapping("/furniture")
    public ResponseEntity<CategoryDTO> addFurnitureCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO addedCategory = categoryService.addFurnitureCategory(categoryDTO);
        return new ResponseEntity<>(addedCategory, HttpStatus.CREATED);
    }

    @GetMapping("/material/{materialCategoryId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByMaterialCategory(@PathVariable Long materialCategoryId) {
        List<PostDTO> posts = postService.getPostsByMaterialCategory(materialCategoryId);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/furniture/{furnitureCategoryId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByFurnitureCategory(@PathVariable Long furnitureCategoryId) {
        List<PostDTO> posts = postService.getPostsByFurnitureCategory(furnitureCategoryId);
        return ResponseEntity.ok(posts);
    }

//    @GetMapping("/material/posts")
//    public ResponseEntity<List<PostDTO>> getPostsByMaterialCategory(@RequestParam List<Long> materialCategoryIds) {
//        List<PostDTO> posts = postService.getPostsByMaterialCategory(materialCategoryIds);
//        return ResponseEntity.ok(posts);
//    }
//
//    @GetMapping("/furniture/posts")
//    public ResponseEntity<List<PostDTO>> getPostsByFurnitureCategory(@RequestParam List<Long> furnitureCategoryIds) {
//        List<PostDTO> posts = postService.getPostsByFurnitureCategory(furnitureCategoryIds);
//        return ResponseEntity.ok(posts);
//    }
}

