package com.example.vladimir.controllers;

import com.example.vladimir.models.Review;
import com.example.vladimir.services.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/companies/{companyId}")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAll(@PathVariable("companyId") Long id){
        return new ResponseEntity<>(reviewService.getAllReviews(id), HttpStatus.OK);
    }

    //Получение определенного review из company
    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getById(@PathVariable("companyId") Long companyId, @PathVariable("reviewId")Long reviewId){
        return new ResponseEntity<>(reviewService.getReviewById(companyId, reviewId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> add(@PathVariable("companyId")Long id, @RequestBody Review review){
        ResponseEntity<String> responseResult = new ResponseEntity<>("Review Added Successfully", HttpStatus.NOT_FOUND);;

        if(reviewService.addReview(id, review))
            responseResult = new ResponseEntity<>("Review Added Successfully", HttpStatus.OK);

        return responseResult;
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> update(@PathVariable("companyId")Long companyId,
                                         @PathVariable("reviewId")Long reviewId,
                                         @RequestBody Review review){
        boolean result = reviewService.updateReview(companyId, reviewId, review);
        return new ResponseEntity<>("Review was updated: " + result, HttpStatus.OK);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> delete(@PathVariable("reviewId")Long reviewId, @PathVariable("companyId")Long companyId){
        reviewService.deleteReview(reviewId, companyId);
        return new ResponseEntity<>("Review was deleted", HttpStatus.OK);
    }
}
