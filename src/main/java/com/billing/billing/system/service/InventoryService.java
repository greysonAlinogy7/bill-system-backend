package com.billing.billing.system.service;

import com.billing.billing.system.mapper.InventoryMapper;
import com.billing.billing.system.model.Branch;
import com.billing.billing.system.model.Inventory;
import com.billing.billing.system.model.Product;
import com.billing.billing.system.payload.dto.InventoryDTO;
import com.billing.billing.system.repository.BranchRepository;
import com.billing.billing.system.repository.InventoryRepository;
import com.billing.billing.system.repository.ProductRepository;
import com.billing.billing.system.service.impl.IInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService implements IInventoryService {

    private  final InventoryRepository inventoryRepository;
    private  final BranchRepository branchRepository;
    private  final ProductRepository productRepository;

    @Override
    public InventoryDTO createInventory(InventoryDTO inventoryDTO) throws Exception {

        Branch branch = branchRepository.findById(inventoryDTO.getBranchId()).orElseThrow(() -> new Exception("branch does not exist..."));
        Product product = productRepository.findById(inventoryDTO.getProductId()).orElseThrow(() -> new Exception("product not found"));

        Inventory inventory = InventoryMapper.toEntity(inventoryDTO, branch, product);
        Inventory savedInventory = inventoryRepository.save(inventory);
        return InventoryMapper.toDTO(savedInventory);
    }

    @Override
    public InventoryDTO updateInventory(Long id, InventoryDTO inventoryDTO) throws Exception {

        Inventory inventory=inventoryRepository.findById(id).orElseThrow(() -> new Exception("inventory not found"));

        inventory.setQuantity(inventoryDTO.getQuantity());
        Inventory updatedInventory = inventoryRepository.save(inventory);

        return InventoryMapper.toDTO(updatedInventory);
    }

    @Override
    public void deleteInventory(Long id) throws Exception {
        Inventory inventory=inventoryRepository.findById(id).orElseThrow(() -> new Exception("inventory not found"));
        inventoryRepository.delete(inventory);
    }

    @Override
    public InventoryDTO getInventoryById(Long id) throws Exception {
        Inventory inventory=inventoryRepository.findById(id).orElseThrow(() -> new Exception("inventory not found"));
        return InventoryMapper.toDTO(inventory);
    }

    @Override
    public InventoryDTO getInventoryByProductIdAndBranchId(Long productId, Long branchId) {
        Inventory inventory = inventoryRepository.findByProductIdAndBranchId(productId, branchId);
        return InventoryMapper.toDTO(inventory);
    }

    @Override
    public List<InventoryDTO> getAllInventoryByBranchId(Long branchId) {
        List<Inventory> inventories = inventoryRepository.findByBranchId(branchId);
        return inventories.stream().map(InventoryMapper::toDTO).collect(Collectors.toList());
    }
}
