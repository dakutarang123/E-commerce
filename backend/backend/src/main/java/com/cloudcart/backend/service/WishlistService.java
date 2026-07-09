package com.cloudcart.backend.service;

import com.cloudcart.backend.entity.Wishlist;
import com.cloudcart.backend.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.cloudcart.backend.dto.WishlistResponse;
import com.cloudcart.backend.entity.Product;
import com.cloudcart.backend.repository.ProductRepository;

import java.util.ArrayList;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;
    @Autowired
    private ProductRepository productRepository;

    public Wishlist addToWishlist(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    public List<WishlistResponse> getWishlistByUser(String email) {

        List<Wishlist> wishlist =
                wishlistRepository.findByUserEmail(email);

        List<WishlistResponse> response =
                new ArrayList<>();

        for (Wishlist item : wishlist) {

            Product product =
                    productRepository.findById(item.getProductId())
                            .orElse(null);

            if (product != null) {

                WishlistResponse dto =
                        new WishlistResponse();

                dto.setId(item.getId());
                dto.setProductId(product.getId());

                dto.setName(product.getName());
                dto.setCategory(product.getCategory());
                dto.setPrice(product.getPrice());
                dto.setImageUrl(product.getImageUrl());

                response.add(dto);

            }

        }

        return response;

    }

    public void removeFromWishlist(Long id) {
        wishlistRepository.deleteById(id);
    }
}