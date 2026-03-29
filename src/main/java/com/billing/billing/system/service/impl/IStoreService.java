package com.billing.billing.system.service.impl;

import com.billing.billing.system.model.Store;
import com.billing.billing.system.model.User;
import com.billing.billing.system.payload.dto.StoreDTO;

import java.util.List;

public interface IStoreService {
    StoreDTO createStore(StoreDTO storeDTO, User user);
    StoreDTO getStoreById(Long id);
    List<StoreDTO> getAllStores();
    Store getStoreByAdmin();
    StoreDTO updateStore(Long id, StoreDTO storeDTO);
    StoreDTO deleteStore(Long id);
    StoreDTO getStoreByEmployee();

}
