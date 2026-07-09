package com.cloudcart.backend.service;

import com.cloudcart.backend.entity.Cart;
import com.cloudcart.backend.repository.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart addToCart(Cart cart){
        return cartRepository.save(cart);
    }

    public List<Cart> getUserCart(String email){
        return cartRepository.findByUserEmail(email);
    }

    public void removeCartItem(Long id){
        cartRepository.deleteById(id);
    }

    @Transactional
    public void clearCart(String email){
        cartRepository.deleteByUserEmail(email);
    }
}