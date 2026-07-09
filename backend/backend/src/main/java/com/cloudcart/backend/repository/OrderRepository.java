package com.cloudcart.backend.repository;

import com.cloudcart.backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository
        extends JpaRepository<Order, Long> {

    List<Order> findByUserEmail(String userEmail);

    @Query("SELECT SUM(o.totalAmount) FROM Order o")
    Double getTotalRevenue();

}