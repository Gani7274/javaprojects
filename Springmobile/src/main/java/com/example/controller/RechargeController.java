package com.example.controller;

import com.example.recharge.Recharge;
import com.example.service.RechargeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recharges")
public class RechargeController {

    private final RechargeService rechargeService;

    public RechargeController(RechargeService rechargeService) {
        this.rechargeService = rechargeService;
    }

    // 1. POST /api/recharges -> Create a recharge
    @PostMapping
    public ResponseEntity<Recharge> createRecharge(@Valid @RequestBody Recharge recharge) {
        Recharge savedRecharge = rechargeService.processRecharge(recharge);
        return new ResponseEntity<>(savedRecharge, HttpStatus.CREATED);
    }

    // 2. GET /api/recharges -> Fetch all recharges
    @GetMapping
    public ResponseEntity<List<Recharge>> getAllRecharges() {
        return ResponseEntity.ok(rechargeService.getAllRecharges());
    }

    // 3. GET /api/recharges/{id} -> Fetch a single recharge by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getRechargeById(@PathVariable Long id) {
        Recharge recharge = rechargeService.getRechargeById(id);
        if (recharge == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Recharge record not found for ID: " + id);
        }
        return ResponseEntity.ok(recharge);
    }
}