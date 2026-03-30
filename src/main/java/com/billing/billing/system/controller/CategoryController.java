package com.billing.billing.system.controller;

import com.billing.billing.system.payload.dto.CategoryDTO;
import com.billing.billing.system.payload.response.ApiResponse;
import com.billing.billing.system.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {
    private  final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) throws Exception {
        return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<List<CategoryDTO>> getCategoryByStoreId(@PathVariable Long storeId) throws Exception {
        return ResponseEntity.ok(categoryService.getCategoriesByStore(storeId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) throws Exception {
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) throws Exception {
        categoryService.deleteCategory(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("category deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }
}
