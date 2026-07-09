package com.cloudcart.backend.controller;

import com.cloudcart.backend.dto.WishlistResponse;
import com.cloudcart.backend.entity.Wishlist;
import com.cloudcart.backend.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@CrossOrigin(origins = "http://localhost:3000")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/add")
    public Wishlist addToWishlist(@RequestBody Wishlist wishlist) {
        return wishlistService.addToWishlist(wishlist);
    }

    @GetMapping("/{email}")
    public List<WishlistResponse> getWishlist(@PathVariable String email) {
        return wishlistService.getWishlistByUser(email);
    }

    @DeleteMapping("/{id}")
    public String removeWishlist(@PathVariable Long id) {
        wishlistService.removeFromWishlist(id);
        return "Item removed from wishlist";
    }
}