package com.billing.billing.system.service;

import com.billing.billing.system.domain.UserRole;
import com.billing.billing.system.mapper.CategoryMapper;
import com.billing.billing.system.model.Category;
import com.billing.billing.system.model.Store;
import com.billing.billing.system.model.User;
import com.billing.billing.system.payload.dto.CategoryDTO;
import com.billing.billing.system.repository.CategoryRepository;
import com.billing.billing.system.repository.StoreRepository;
import com.billing.billing.system.service.impl.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private  final CategoryRepository categoryRepository;
    private  final UserService userService;
    private  final StoreRepository storeRepository;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) throws Exception {
        User user = userService.getCurrentUser();
        Store store = storeRepository.findById(categoryDTO.getStoreId()).orElseThrow(() -> new Exception("Store not found"));
        Category category = Category.builder()
                .store(store)
                .name(categoryDTO.getName())
                .build();
        return CategoryMapper.toDTO(category);
    }

    @Override
    public List<CategoryDTO> getCategoriesByStore(Long storeId) {
        List<Category> categories = categoryRepository.findByStoreId(storeId);
        return categories.stream().map(CategoryMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) throws Exception {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new Exception("category not found"));
        User user = userService.getCurrentUser();
        category.setName(categoryDTO.getName());
        return CategoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) throws Exception {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new Exception("category not found"));
        User user = userService.getCurrentUser();
        categoryRepository.delete(category);

    }

    private  void checkAuthority(User user, Store store) throws Exception {
        boolean isAdmin = user.getRole().equals(UserRole.ROLE_ADMIN);
        boolean isManager = user.getRole().equals(UserRole.ROLE_STORE_MANAGER);
        boolean isSameStore = user.equals(store.getStoreAdmin());

        if (!(isAdmin && isSameStore) && !isManager){
            throw  new Exception("You don't have permission to manage this category");

        }

    }
}
