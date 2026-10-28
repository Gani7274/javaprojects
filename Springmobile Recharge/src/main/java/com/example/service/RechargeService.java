package com.example.service;

import com.example.recharge.Recharge;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class RechargeService {

    private final List<Recharge> rechargeList = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Recharge processRecharge(Recharge recharge) {
        recharge.setRechargeId(idGenerator.getAndIncrement());
        rechargeList.add(recharge);
        return recharge;
    }

    public List<Recharge> getAllRecharges() {
        return rechargeList;
    }

    public Recharge getRechargeById(Long rechargeId) {
        return rechargeList.stream()
                .filter(r -> r.getRechargeId() != null && r.getRechargeId().equals(rechargeId))
                .findFirst()
                .orElse(null);
    }
}