package com.billing.billing.system.controller;

import com.billing.billing.system.exception.UserException;
import com.billing.billing.system.model.Branch;
import com.billing.billing.system.payload.dto.BranchDTO;
import com.billing.billing.system.payload.response.ApiResponse;
import com.billing.billing.system.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/branches")
public class BranchController {
    private  final BranchService branchService;


    @PostMapping()
    public ResponseEntity<BranchDTO> createBranch(@RequestBody BranchDTO branchDTO) throws UserException {
        BranchDTO createBranch = branchService.createBranch(branchDTO);
        return ResponseEntity.ok(createBranch);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchDTO> getBranchById(@PathVariable Long id) throws Exception {
        BranchDTO createBranch = branchService.getBranchById(id);
        return ResponseEntity.ok(createBranch);
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<BranchDTO>> getAllBranchByStoreId(@PathVariable Long storeId) throws Exception {
        List<BranchDTO> createBranch = branchService.getAllBranchByStoreId(storeId);
        return ResponseEntity.ok(createBranch);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchDTO> updateBranch(@PathVariable Long id, @RequestBody BranchDTO branchDTO) throws Exception {
        BranchDTO createBranch = branchService.updateBranch(id, branchDTO);
        return ResponseEntity.ok(createBranch);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBranchById(@PathVariable Long id) throws Exception {
         branchService.deleteBranch(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("branch deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }


}
