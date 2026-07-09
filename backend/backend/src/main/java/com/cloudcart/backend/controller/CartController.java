
package com.cloudcart.backend.controller;

import com.cloudcart.backend.entity.Cart;
import com.cloudcart.backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public Cart addToCart(@RequestBody Cart cart){
        return cartService.addToCart(cart);
    }

    @GetMapping("/{email}")
    public List<Cart> getUserCart(@PathVariable String email){
        return cartService.getUserCart(email);
    }

    @DeleteMapping("/{id}")
    public String removeCartItem(@PathVariable Long id){
        cartService.removeCartItem(id);
        return "Item Removed";
    }
}