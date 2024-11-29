package com.example.vladimir.services.impl;

import com.example.vladimir.models.Company;
import com.example.vladimir.models.Review;
import com.example.vladimir.repositories.CompanyRepository;
import com.example.vladimir.repositories.ReviewRepository;
import com.example.vladimir.services.CompanyService;
import com.example.vladimir.services.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);

        return reviews;
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = getAllReviews(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findAny().orElseThrow();
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        boolean result = false;
        if(company != null){
            review.setCompany(company);
            reviewRepository.save(review);
            result = true;
        }
        return result;
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updateReview) {
        boolean result = false;

        if(companyService.getCompanyById(companyId) != null){
            updateReview.setCompany(companyService.getCompanyById(companyId));
            updateReview.setId(reviewId);
            reviewRepository.save(updateReview);
            result = true;
        }
        return result;
    }

    @Override
    public boolean deleteReview(Long reviewId, Long companyId) {
        boolean result = false;
        if(companyService.getCompanyById(companyId) != null && reviewRepository.existsById(reviewId)){
            Review review = reviewRepository.findById(reviewId).orElse(null);
            Company company = review.getCompany();
            company.getReviews().remove(review);
            companyService.updateCompany(companyId, company);
            reviewRepository.deleteById(reviewId);
            result = true;
        }
        return result;
    }


}
