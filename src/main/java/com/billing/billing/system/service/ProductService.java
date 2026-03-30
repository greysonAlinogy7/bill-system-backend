package com.billing.billing.system.service;

import com.billing.billing.system.mapper.ProductMapper;
import com.billing.billing.system.model.Category;
import com.billing.billing.system.model.Product;
import com.billing.billing.system.model.Store;
import com.billing.billing.system.model.User;
import com.billing.billing.system.payload.dto.ProductDTO;
import com.billing.billing.system.repository.CategoryRepository;
import com.billing.billing.system.repository.ProductRepository;
import com.billing.billing.system.repository.StoreRepository;
import com.billing.billing.system.service.impl.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private  final ProductRepository productRepository;
    private  final StoreRepository storeRepository;
    private  final CategoryRepository categoryRepository;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO, User user) throws Exception {

        Store store = storeRepository.findById(productDTO.getStoreId()).orElseThrow(() -> new Exception("store not found"));
        Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(() -> new Exception("category not found"));


        Product product = ProductMapper.toEntity(productDTO, store, category);


        Product savedProduct= productRepository.save(product);
        return ProductMapper.toDTO(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO, User user) throws Exception {

        Product product = productRepository.findById(id).orElseThrow(() -> new Exception("product not found"));

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setSku(productDTO.getSku());
        product.setImage(productDTO.getImage());
        product.setMrp(productDTO.getMrp());
        product.setSellingPrice(productDTO.getSellingPrice());
        product.setBrand(productDTO.getBrand());
        product.setUpdatedAt(productDTO.getUpdatedAt());
        if (productDTO.getCategoryId() != null){
            Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(() -> new Exception("category not found"));
            product.setCategory(category);
        }

        Product savedProduct = productRepository.save(product);
        return ProductMapper.toDTO(savedProduct);
    }

    @Override
    public void deleteProduct(Long id, User user) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(() -> new Exception("product not found"));
        productRepository.delete(product);
    }

    @Override
    public List<ProductDTO> getProductsByStoreId(Long storeId) {
        List<Product> productList = productRepository.findByStoreId(storeId);
        return productList.stream().map(ProductMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> searchByKeyword(Long storeId, String keyword) {
        List<Product> productList = productRepository.searchByKeyword(storeId, keyword);
        return productList.stream().map(ProductMapper::toDTO).collect(Collectors.toList());
    }
}
