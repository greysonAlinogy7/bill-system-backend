package com.billing.billing.system.service.impl;

import com.billing.billing.system.model.User;
import com.billing.billing.system.payload.dto.ProductDTO;

import java.util.List;

public interface IProductService {
    ProductDTO createProduct(ProductDTO productDTO, User user) throws Exception;
    ProductDTO updateProduct(Long id, ProductDTO productDTO, User user) throws Exception;
    void deleteProduct(Long id, User user) throws Exception;
    List<ProductDTO> getProductsByStoreId(Long storeId);
    List<ProductDTO> searchByKeyword(Long storeId, String keyword);
}
