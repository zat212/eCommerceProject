package com.lastProject.Service;

import com.lastProject.Entity.Customer;
import com.lastProject.Entity.Order;
import com.lastProject.Entity.OrderItem;
import com.lastProject.Repository.OrderItemRepository;
import com.lastProject.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<Order> findOrders(Customer customer){
        return orderRepository.findOrderByCustomer(customer);
    }


}
