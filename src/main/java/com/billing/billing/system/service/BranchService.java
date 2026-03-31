package com.billing.billing.system.service;

import com.billing.billing.system.exception.UserException;
import com.billing.billing.system.mapper.BranchMapper;
import com.billing.billing.system.model.Branch;
import com.billing.billing.system.model.Store;
import com.billing.billing.system.model.User;
import com.billing.billing.system.payload.dto.BranchDTO;
import com.billing.billing.system.repository.BranchRepository;
import com.billing.billing.system.repository.StoreRepository;
import com.billing.billing.system.service.impl.IBranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BranchService implements IBranchService {
    private  final BranchRepository branchRepository;
    private final StoreRepository storeRepository;
    private  final UserService userService;

    @Override
    public BranchDTO createBranch(BranchDTO branchDTO) throws UserException {

        User currentUser = userService.getCurrentUser();
        Store store = storeRepository.findByStoreAdminId(currentUser.getId());
        if (store == null) {
            throw new RuntimeException("No store found for this user. Create a store first.");
        }
        Branch branch = BranchMapper.toEntity(branchDTO, store);
        Branch savedBranch = branchRepository.save(branch);
        return BranchMapper.toDTO(savedBranch);
    }

    @Override
    public BranchDTO updateBranch(Long id, BranchDTO branchDTO) throws Exception {
        Branch existing = branchRepository.findById(id).orElseThrow(() -> new Exception("branch does not exist"));
        existing.setName(branchDTO.getName());
        existing.setWorkingDays(branchDTO.getWorkingDays());
        existing.setEmail(branchDTO.getEmail());
        existing.setPhone(branchDTO.getPhone());
        existing.setAddress(branchDTO.getAddress());
        existing.setOpenTime(branchDTO.getOpenTime());
        existing.setCloseTime(branchDTO.getCloseTime());
        existing.setUpdateAt(LocalDateTime.now());

        Branch updatedBranch = branchRepository.save(existing);
        return BranchMapper.toDTO(updatedBranch);
    }

    @Override
    public List<BranchDTO> getAllBranchByStoreId(Long storeId) {
        List<Branch> branches = branchRepository.findByStoreId(storeId);
       return branches.stream().map(BranchMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public BranchDTO getBranchById(Long id) throws Exception {
        Branch existing = branchRepository.findById(id).orElseThrow(() -> new Exception("branch not exist"));
        return BranchMapper.toDTO(existing);
    }

    @Override
    public void deleteBranch(Long id) throws Exception {
        Branch existing = branchRepository.findById(id).orElseThrow(() -> new Exception("branch not exist"));
        branchRepository.delete(existing);
    }
}
