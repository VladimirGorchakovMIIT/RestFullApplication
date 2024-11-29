package com.example.vladimir.controllers.graphql;

import com.example.vladimir.models.Company;
import com.example.vladimir.models.Review;
import com.example.vladimir.services.impl.CompanyServiceImpl;
import com.example.vladimir.services.impl.ReviewServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ReviewControllerGraphQL {
    private final ReviewServiceImpl reviewService;
    private final CompanyServiceImpl companyService;

    @QueryMapping
    public List<Review> getAllReviews(@Argument Long id){
        return reviewService.getAllReviews(id);
    }

    @QueryMapping
    public Review getReviewById(@Argument Long companyId, @Argument Long reviewId){
        return reviewService.getReviewById(companyId, reviewId);
    }

    @MutationMapping
    public Boolean deleteReviewById(@Argument Long reviewId, @Argument Long companyId){
        return reviewService.deleteReview(reviewId, companyId);
    }

    @MutationMapping
    public String createReview(@Argument ReviewInput reviewInput, @Argument Long companyId){
        String result = "Отзыв был создан";

        Company company = companyService.getCompanyById(companyId);
        Review review = new Review(reviewInput.title, reviewInput.description, Double.parseDouble(reviewInput.rating), company);

        reviewService.addReview(companyId, review);

        return result;
    }

    @MutationMapping
    public String updateReview(@Argument Long reviewId, @Argument ReviewInput reviewInput, @Argument Long companyId){

        Company company = companyService.getCompanyById(companyId);
        Review review = new Review(reviewInput.title, reviewInput.description, Double.parseDouble(reviewInput.rating), company);

        reviewService.updateReview(companyId, reviewId, review);

        return "Отзыв был обновлен";
    }

    record ReviewInput(String title, String description, String rating){}

}
