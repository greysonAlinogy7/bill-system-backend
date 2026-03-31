package com.billing.billing.system.service.impl;

import com.billing.billing.system.exception.UserException;
import com.billing.billing.system.model.User;
import com.billing.billing.system.payload.dto.BranchDTO;

import java.util.List;

public interface IBranchService {
    BranchDTO createBranch(BranchDTO branchDTO) throws UserException;
    BranchDTO updateBranch(Long id, BranchDTO branchDTO) throws Exception;
    List<BranchDTO> getAllBranchByStoreId(Long storeId);
    BranchDTO getBranchById(Long id) throws Exception;
    void deleteBranch(Long id) throws Exception;
}
