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

    // 1. Create order
    public FoodOrder placeOrder(FoodOrder order) {
        order.setOrderId(idGenerator.getAndIncrement());
        orderList.add(order);
        return order;
    }

    // 2. Get all orders
    public List<FoodOrder> getAllOrders() {
        return orderList;
    }

    // 3. Get order by ID
    public FoodOrder getOrderById(Long orderId) {
        return orderList.stream()
                .filter(o -> o.getOrderId() != null && o.getOrderId().equals(orderId))
                .findFirst()
                .orElse(null);
    }

    // 4. Full update (PUT)
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

    // 5. Partial update (PATCH)
    public FoodOrder patchOrder(Long orderId, Map<String, Object> updates) {
        FoodOrder existing = getOrderById(orderId);
        if (existing == null) {
            return null;
        }

        updates.forEach((key, value) -> {
            switch (key) {
                case "customerName" -> existing.setCustomerName((String) value);
                case "foodItem" -> existing.setFoodItem((String) value);
                case "quantity" -> existing.setQuantity(((Number) value).intValue());
                case "price" -> existing.setPrice(((Number) value).doubleValue());
            }
        });

        return existing;
    }

    // 6. Delete order
    public boolean deleteOrder(Long orderId) {
        return orderList.removeIf(o -> o.getOrderId() != null && o.getOrderId().equals(orderId));
    }
}