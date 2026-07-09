package com.cloudcart.backend.service;

import com.cloudcart.backend.entity.Review;
import com.cloudcart.backend.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    // Add Review
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    // Get Reviews By Product
    public List<Review> getReviews(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    // Delete Review
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
    public Double getAverageRating(Long productId) {

        Double avg = reviewRepository.getAverageRating(productId);

        return avg == null ? 0 : avg;
    }
}