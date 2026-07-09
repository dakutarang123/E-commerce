package com.cloudcart.backend.controller;

import com.cloudcart.backend.entity.Review;
import com.cloudcart.backend.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin("*")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Add Review
    @PostMapping("/add")
    public Review addReview(@RequestBody Review review) {

        return reviewService.addReview(review);

    }

    // Get Reviews By Product
    @GetMapping("/{productId}")
    public List<Review> getReviews(
            @PathVariable Long productId) {

        return reviewService.getReviews(productId);

    }

    // Delete Review
    @DeleteMapping("/{id}")
    public String deleteReview(
            @PathVariable Long id) {

        reviewService.deleteReview(id);

        return "Review Deleted";

    }
    @GetMapping("/average/{productId}")
    public Double getAverageRating(
            @PathVariable Long productId) {

        return reviewService.getAverageRating(productId);

    }
}