package com.billing.billing.system.mapper;

import com.billing.billing.system.model.Branch;
import com.billing.billing.system.model.Inventory;
import com.billing.billing.system.model.Product;
import com.billing.billing.system.payload.dto.InventoryDTO;

public class InventoryMapper {
    public  static InventoryDTO toDTO(Inventory inventory){
        return InventoryDTO.builder()
                .id(inventory.getId())
                .branchId(inventory.getBranch().getId())
                .productId(inventory.getProduct().getId())
                .product(ProductMapper.toDTO(inventory.getProduct()))
                .quantity(inventory.getQuantity())
                .lastUpdate(inventory.getLastUpdate())
                .build();
    }

    public  static Inventory toEntity(InventoryDTO inventoryDTO, Branch branch, Product product){
        return Inventory.builder()
                .branch(branch)
                .product(product)
                .quantity(inventoryDTO.getQuantity())
                .build();
    }
}
