package com.cloudcart.backend.controller;

import com.cloudcart.backend.repository.OrderRepository;
import com.cloudcart.backend.repository.ProductRepository;
import com.cloudcart.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:3000")
public class DashboardController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/products/count")
    public long totalProducts() {

        return productRepository.count();

    }
    @GetMapping("/orders/count")
    public long totalOrders() {

        return orderRepository.count();

    }
    @GetMapping("/revenue")
    public Double totalRevenue() {
        Double revenue = orderRepository.getTotalRevenue();
        return revenue == null ? 0.0 : revenue;
    }
    @GetMapping("/users/count")
    public long totalUsers() {

        return userRepository.count();

    }
}