package com.billing.billing.system.service;

import com.billing.billing.system.model.Store;
import com.billing.billing.system.model.User;
import com.billing.billing.system.payload.dto.StoreDTO;
import com.billing.billing.system.repository.StoreRepository;
import com.billing.billing.system.service.impl.IStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService implements IStoreService {
    private  final StoreRepository storeRepository;
    private  final UserService userService;

    @Override
    public StoreDTO createStore(StoreDTO storeDTO, User user) {
        return null;
    }

    @Override
    public StoreDTO getStoreById(Long id) {
        return null;
    }

    @Override
    public List<StoreDTO> getAllStores() {
        return List.of();
    }

    @Override
    public Store getStoreByAdmin() {
        return null;
    }

    @Override
    public StoreDTO updateStore(Long id, StoreDTO storeDTO) {
        return null;
    }

    @Override
    public StoreDTO deleteStore(Long id) {
        return null;
    }

    @Override
    public StoreDTO getStoreByEmployee() {
        return null;
    }
}
