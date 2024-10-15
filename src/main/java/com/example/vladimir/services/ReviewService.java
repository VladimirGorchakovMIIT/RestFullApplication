package com.example.vladimir.services;

import com.example.vladimir.models.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);

    Review getReviewById(Long companyId, Long reviewId);

    boolean addReview(Long id, Review review);
    boolean updateReview(Long companyId, Long reviewId, Review review);
    boolean deleteReview(Long reviewId, Long companyId);
}
