package com.example.controller;

import com.example.foodservice.foodorderservice;
import com.examplefood.FoodOrder;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class foodordercontroller {

    private final foodorderservice orderService;

    public foodordercontroller(foodorderservice orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<FoodOrder> createOrder(@Valid @RequestBody FoodOrder order) {
        FoodOrder saved = orderService.placeOrder(order);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FoodOrder>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        FoodOrder order = orderService.getOrderById(id);
        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Order not found with ID: " + id);
        }
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @Valid @RequestBody FoodOrder order) {
        FoodOrder updated = orderService.updateOrder(id, order);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cannot update. Order not found with ID: " + id);
        }
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchOrder(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        FoodOrder patched = orderService.patchOrder(id, updates);
        if (patched == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cannot patch. Order not found with ID: " + id);
        }
        return ResponseEntity.ok(patched);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        boolean removed = orderService.deleteOrder(id);
        if (!removed) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cannot delete. Order not found with ID: " + id);
        }
        return ResponseEntity.ok("Order with ID " + id + " has been successfully deleted.");
    }
}