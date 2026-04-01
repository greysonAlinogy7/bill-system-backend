package com.billing.billing.system.controller;

import com.billing.billing.system.payload.dto.InventoryDTO;
import com.billing.billing.system.payload.response.ApiResponse;
import com.billing.billing.system.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventories")
public class InventoryController {
    private  final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<InventoryDTO> create(@RequestBody InventoryDTO inventoryDTO) throws Exception {
        return ResponseEntity.ok(inventoryService.createInventory(inventoryDTO));
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<InventoryDTO>> getInventoryByBranch(@PathVariable Long branchId) throws Exception {
        return ResponseEntity.ok(inventoryService.getAllInventoryByBranchId(branchId));
    }

    @GetMapping("/branch/{branchId}/product/{productId}")
    public ResponseEntity<InventoryDTO> getInventoryByProductAndBranchId(@PathVariable Long branchId, @PathVariable Long productId) throws Exception {
        return ResponseEntity.ok(inventoryService.getInventoryByProductIdAndBranchId(productId, branchId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) throws Exception {
        inventoryService.deleteInventory(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("inventory deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDTO> update(@PathVariable Long id,@RequestBody InventoryDTO inventoryDTO) throws Exception {
        return ResponseEntity.ok(inventoryService.updateInventory(id, inventoryDTO));
    }


}
