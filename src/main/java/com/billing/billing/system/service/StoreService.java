package com.billing.billing.system.service;

import com.billing.billing.system.exception.UserException;
import com.billing.billing.system.mapper.StoreMapper;
import com.billing.billing.system.model.Store;
import com.billing.billing.system.model.StoreContact;
import com.billing.billing.system.model.User;
import com.billing.billing.system.payload.dto.StoreDTO;
import com.billing.billing.system.repository.StoreRepository;
import com.billing.billing.system.service.impl.IStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService implements IStoreService {
    private  final StoreRepository storeRepository;
    private  final UserService userService;

    @Override
    public StoreDTO createStore(StoreDTO storeDTO, User user) {

        Store store = StoreMapper.toEntity(storeDTO, user);
        return StoreMapper.toDTO(storeRepository.save(store));
    }

    @Override
    public StoreDTO getStoreById(Long id) throws Exception {
        Store store = storeRepository.findById(id).orElseThrow(() -> new Exception("store not found"));
        return StoreMapper.toDTO(store);
    }

    @Override
    public List<StoreDTO> getAllStores() {

        List<Store> dtos = storeRepository.findAll();
       return dtos.stream().map(StoreMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Store getStoreByAdmin() throws UserException {
        User admin = userService.getCurrentUser();
        return storeRepository.findByStoreAdminId(admin.getId());
    }

    @Override
    public StoreDTO updateStore(Long id, StoreDTO storeDTO) throws Exception {
        User currentUser = userService.getCurrentUser();
        Store existingStore = storeRepository.findByStoreAdminId(currentUser.getId());
        if (existingStore==null){
            throw  new Exception("store not found");
        }
        existingStore.setBrand(storeDTO.getBrand());
        existingStore.setDescription(storeDTO.getDescription());

        if (storeDTO.getStoreType() != null){
            existingStore.setStoreType(storeDTO.getStoreType());
        }

        if (storeDTO.getContact() != null){
            StoreContact contact = StoreContact.builder()
                    .address(storeDTO.getContact().getAddress())
                    .phone(storeDTO.getContact().getPhone())
                    .email(storeDTO.getContact().getEmail())
                    .build();
            existingStore.setContact(contact);
        }

        Store updatedStore =storeRepository.save(existingStore);

        return StoreMapper.toDTO(updatedStore);
    }

    @Override
    public void deleteStore(Long id) throws UserException {
        Store store = getStoreByAdmin();
        storeRepository.delete(store);
    }

    @Override
    public StoreDTO getStoreByEmployee() throws UserException {
        User currentUser = userService.getCurrentUser();
        if (currentUser==null){
            throw  new UserException("you don't have permission to access this store");
        }

        return StoreMapper.toDTO(currentUser.getStore());
    }
}
