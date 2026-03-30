package com.billing.billing.system.mapper;

import com.billing.billing.system.model.Store;
import com.billing.billing.system.model.User;
import com.billing.billing.system.payload.dto.StoreDTO;
import org.springframework.stereotype.Component;

@Component
public class StoreMapper {
    public static StoreDTO toDTO(Store store){
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setId(store.getId());
        storeDTO.setBrand(store.getBrand());
        storeDTO.setDescription(store.getDescription());
        storeDTO.setStoreAdmin(UserMapper.toDTO(store.getStoreAdmin()));
        storeDTO.setStoreType(store.getStoreType());
        storeDTO.setContact(store.getContact());
        storeDTO.setCreatedAt(store.getCreatedAt());
        storeDTO.setUpdatedAt(store.getUpdatedAt());
        storeDTO.setStatus(store.getStatus());
        return storeDTO;

    }

    public  static Store toEntity(StoreDTO storeDTO, User storeAdmin){
        Store store = new Store();
        store.setId(storeDTO.getId());
        store.setBrand(storeDTO.getBrand());
        store.setDescription(storeDTO.getDescription());
        store.setStoreAdmin(storeAdmin);
        store.setStoreType(storeDTO.getStoreType());
        store.setContact(storeDTO.getContact());
        store.setCreatedAt(storeDTO.getCreatedAt());
        store.setUpdatedAt(storeDTO.getUpdatedAt());


        return store;
    }
}
