package com.programmingtechi.inventory_service.controller;

import com.programmingtechi.inventory_service.dto.InventoryRequest;
import com.programmingtechi.inventory_service.dto.InventoryResponse;
import com.programmingtechi.inventory_service.model.Inventory;
import com.programmingtechi.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        log.info("Received inventory check request for skuCode: {}", skuCode);
        return inventoryService.isInStock(skuCode);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Inventory> addProductToInventory(@RequestBody InventoryRequest inventoryRequest) {
        Inventory inventory = inventoryService.addProductToInventory(inventoryRequest.getSkuCode(), inventoryRequest.getQuantity());
        return ResponseEntity.ok(inventory);
    }

    @PutMapping("/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Inventory> updateInventoryQuantity(@PathVariable String skuCode, @RequestBody InventoryRequest inventoryUpdateRequest) {
        Inventory updatedInventory = inventoryService.updateInventoryQuantity(skuCode, inventoryUpdateRequest.getQuantity());
        return ResponseEntity.ok(updatedInventory);
    }

    @GetMapping("/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Inventory> getInventoryBySkuCode(@PathVariable String skuCode) {
        Inventory inventory = inventoryService.getInventoryBySkuCode(skuCode);
        return ResponseEntity.ok(inventory);
    }

    @DeleteMapping("/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> removeProductFromInventory(@PathVariable String skuCode) {
        inventoryService.removeProductFromInventory(skuCode);
        return ResponseEntity.noContent().build();
    }
}
