package com.lastProject.Repository;

import com.lastProject.Entity.Customer;
import com.lastProject.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    Optional<Order> findByCustomerAndStatus(Customer customer, String status);

    List<Order> findOrderByCustomer(Customer customer);
    List<Order> findByCustomer(Customer customer);

}



