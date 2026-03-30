package com.billing.billing.system.service;

import com.billing.billing.system.model.User;
import com.billing.billing.system.payload.dto.ProductDTO;
import com.billing.billing.system.repository.ProductRepository;
import com.billing.billing.system.service.impl.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private  final ProductRepository productRepository;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO, User user) {
        return null;
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO, User user) {
        return null;
    }

    @Override
    public void deleteProduct(Long id, User user) {

    }

    @Override
    public List<ProductDTO> getProductsByStoreId(Long storeId) {
        return List.of();
    }

    @Override
    public List<ProductDTO> searchByKeyword(Long storeId, String keyword) {
        return List.of();
    }
}
