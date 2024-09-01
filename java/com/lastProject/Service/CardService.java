package com.lastProject.Service;

import com.lastProject.Entity.*;
import com.lastProject.Repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderReportRepository orderReportRepository;

    public void addItemToCart(int customerId, int productId, int quantity) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            Order order = orderRepository.findByCustomerAndStatus(customer, "cart").orElseGet(() -> {
                Order newOrder = new Order();
                newOrder.setCustomer(customer);
                newOrder.setStatus("cart");
                newOrder.setOrderDate(new Date());
                return orderRepository.save(newOrder);
            });

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getProductAvailable() < quantity) {
                throw new RuntimeException("Insufficient stock available");
            }
            product.setProductAvailable(product.getProductAvailable() - quantity);
            productRepository.save(product);

            OrderItem orderItem = new OrderItem(order, product,order.getOrderDate() , quantity, product.getProductPrice() * quantity);
            orderItemRepository.save(orderItem);
        } else {
            throw new RuntimeException("Customer not found");
        }
    }


    public float calculateTotalAmount(int customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            Order order = orderRepository.findByCustomerAndStatus(customer, "cart").orElse(null);
            if (order != null) {
                return (float) order.getOrderItem().stream().mapToDouble(OrderItem::getPrice).sum();
            }
        }
        return 0;
    }


    public void checkout(int customerId, String billingName, String billingAddress, String billingEmail, String contactNumber1, String contactNumber2) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            Order order = orderRepository.findByCustomerAndStatus(customer, "cart")
                    .orElseThrow(() -> new RuntimeException("No items in cart"));
            double totalPrice = order.getOrderItem().stream()
                    .mapToDouble(OrderItem::getPrice)
                    .sum();
            order.setTotalAmount((float) totalPrice);
            order.setBillingName(billingName);
            order.setBillingAddress(billingAddress);
            order.setBillingEmail(billingEmail);
            order.setContactNumber1(contactNumber1);
            order.setContactNumber2(contactNumber2);
            order.setStatus("ordered");
            order.setOrderDate(new Date());
            orderRepository.save(order);
            OrderReport orderReport = new OrderReport(
                    order.getOrder_id(),
                    customerId,
                    customer.getCusName(),
                    totalPrice,
                    new Date(),
                    billingName,
                    billingAddress,
                    billingEmail,
                    contactNumber1,
                    contactNumber2,
                    "ordered"
            );
            try {
                orderReportRepository.save(orderReport);
            } catch (Exception e) {
                System.err.println("Error saving OrderReport: " + e.getMessage());
                e.printStackTrace();
            }
            orderItemRepository.deleteAll(order.getOrderItem());

        } else {
            throw new RuntimeException("Customer not found");
        }
    }


    public List<OrderItem> getProductsInCart(int customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Order cartOrder = orderRepository.findByCustomerAndStatus(customer, "cart")
                .orElse(null);
        if (cartOrder == null) {
            return new ArrayList<>();
        }
        return cartOrder.getOrderItem();
    }

}
