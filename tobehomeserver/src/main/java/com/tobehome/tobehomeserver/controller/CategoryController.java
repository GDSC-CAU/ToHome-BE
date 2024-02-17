package com.tobehome.tobehomeserver.controller;

import com.tobehome.tobehomeserver.dto.request.CategoryDTO;
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
}

