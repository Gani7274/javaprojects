package com.example.foodservice;

import com.examplefood.FoodOrder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class foodorderservice {

    private final List<FoodOrder> orderList = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public FoodOrder placeOrder(FoodOrder order) {
        order.setOrderId(idGenerator.getAndIncrement());
        orderList.add(order);
        return order;
    }

    public List<FoodOrder> getAllOrders() {
        return orderList;
    }

    public FoodOrder getOrderById(Long orderId) {
        return orderList.stream()
                .filter(o -> o.getOrderId() != null && o.getOrderId().equals(orderId))
                .findFirst()
                .orElse(null);
    }

    public FoodOrder updateOrder(Long orderId, FoodOrder updatedOrder) {
        FoodOrder existing = getOrderById(orderId);
        if (existing == null) {
            return null;
        }
        existing.setCustomerName(updatedOrder.getCustomerName());
        existing.setFoodItem(updatedOrder.getFoodItem());
        existing.setQuantity(updatedOrder.getQuantity());
        existing.setPrice(updatedOrder.getPrice());
        return existing;
    }

    public FoodOrder patchOrder(Long orderId, Map<String, Object> updates) {
        FoodOrder existing = getOrderById(orderId);
        if (existing == null) {
            return null;
        }

        updates.forEach((key, value) -> {
            if (value != null) {
                switch (key) {
                    case "customerName" -> existing.setCustomerName(value.toString());
                    case "foodItem" -> existing.setFoodItem(value.toString());
                    case "quantity" -> existing.setQuantity(((Number) value).intValue());
                    case "price" -> existing.setPrice(((Number) value).doubleValue());
                }
            }
        });

        return existing;
    }

    public boolean deleteOrder(Long orderId) {
        return orderList.removeIf(o -> o.getOrderId() != null && o.getOrderId().equals(orderId));
    }
}