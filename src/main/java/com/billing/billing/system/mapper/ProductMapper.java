package com.billing.billing.system.mapper;

import com.billing.billing.system.model.Category;
import com.billing.billing.system.model.Product;
import com.billing.billing.system.model.Store;
import com.billing.billing.system.payload.dto.ProductDTO;

public class ProductMapper {
    public static ProductDTO toDTO(Product product){
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .description(product.getDescription())
                .mrp(product.getMrp())
                .sellingPrice(product.getSellingPrice())
                .brand(product.getBrand())
                .category(CategoryMapper.toDTO(product.getCategory()))
                .storeId(product.getStore() != null ? product.getStore().getId() : null )
                .image(product.getImage())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    public static Product toEntity(ProductDTO productDTO, Store store, Category category){
        return Product.builder()
                .name(productDTO.getName())
                .category(category)
                .sku(productDTO.getSku())
                .store(store)
                .description(productDTO.getDescription())
                .mrp(productDTO.getMrp())
                .sellingPrice(productDTO.getSellingPrice())
                .brand(productDTO.getBrand())
                .build();
    }
}
