package com.billing.billing.system.service.impl;

import com.billing.billing.system.exception.UserException;
import com.billing.billing.system.payload.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO) throws Exception;
    List<CategoryDTO> getCategoriesByStore(Long storeId);
    CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) throws Exception;
    void deleteCategory(Long id) throws Exception;
}
