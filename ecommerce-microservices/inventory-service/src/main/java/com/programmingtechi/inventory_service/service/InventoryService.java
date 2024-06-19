package com.programmingtechi.inventory_service.service;
import com.programmingtechi.inventory_service.dto.InventoryResponse;
import com.programmingtechi.inventory_service.model.Inventory;
import com.programmingtechi.inventory_service.repisitory.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        log.info("Checking Inventory");
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                        InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity() > 0)
                                .build()
                ).toList();
    }
//    @Transactional
    public Inventory addProductToInventory(String skuCode, int quantity) {//проверка на наличие записи с таким артикулом
        Inventory inventory = inventoryRepository.findBySkuCode(skuCode);
        if (inventory == null) {
            inventory = Inventory.builder()
                    .skuCode(skuCode)
                    .quantity(quantity)
                    .build();
            return inventoryRepository.save(inventory);
        } else {
            inventory.setQuantity(inventory.getQuantity() + quantity);
            return inventoryRepository.save(inventory);
        }
    }
    @Transactional
    public Inventory updateInventoryQuantity(String skuCode, int quantity) {
        Inventory inventory = inventoryRepository.findBySkuCode(skuCode);
        if (inventory == null) {
            throw new RuntimeException("Inventory not found for the given sku code: " + skuCode);
        }
        inventory.setQuantity(quantity);
        return inventoryRepository.save(inventory);
    }
    @Transactional(readOnly = true)
    public Inventory getInventoryBySkuCode(String skuCode) {
        return inventoryRepository.findBySkuCode(skuCode);
    }
    @Transactional
    public void removeProductFromInventory(String skuCode) {
        Inventory inventory = inventoryRepository.findBySkuCode(skuCode);
        if (inventory != null) {
            inventoryRepository.delete(inventory);
        }
    }
}
