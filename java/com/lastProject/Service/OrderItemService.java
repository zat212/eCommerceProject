package com.lastProject.Service;

import com.lastProject.Entity.Order;
import com.lastProject.Entity.OrderItem;
import com.lastProject.Repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItem> findByOrder(Order order){
        return orderItemRepository.findOrderItemByOrder(order);
    }

    public void updateOrderItemQuantity(int orderItemId, int quantity) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid OrderItem ID"));
        orderItem.setQuantity(quantity);
        orderItem.setPrice(orderItem.getProduct().getProductPrice() * quantity);
        orderItemRepository.save(orderItem);
    }

    public void deleteOrderItem(int orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }

    public List<OrderItem> getDailyReport(Date date) {
        return orderItemRepository.findByOrderDate(date);
    }
}
