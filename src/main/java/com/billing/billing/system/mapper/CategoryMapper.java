package com.billing.billing.system.mapper;

import com.billing.billing.system.model.Category;
import com.billing.billing.system.payload.dto.CategoryDTO;

public class CategoryMapper {
    public static CategoryDTO toDTO(Category category){
        return  CategoryDTO.builder()
                .id(category.getId())
                 .name(category.getName())
                 .storeId(category.getStore() != null ? category.getStore().getId() : null)
                 .build();
    }
}
