package com.cloudcart.backend.repository;

import com.cloudcart.backend.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUserEmail(String userEmail);

    @Modifying
    @Transactional
    void deleteByUserEmail(String userEmail);
}