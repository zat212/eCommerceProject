package com.lastProject.Repository;


import com.lastProject.Entity.Order;
import com.lastProject.Entity.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {

    List<OrderItem> findOrderItemByOrder(Order order);
    List<OrderItem> findByOrder(Order order);

    @Query("SELECT oi FROM OrderItem oi WHERE DATE(oi.orderDate) = :date")
    List<OrderItem> findByOrderDate(@Param("date") Date date);

}
