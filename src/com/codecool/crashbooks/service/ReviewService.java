package com.codecool.crashbooks.service;

import com.codecool.crashbooks.model.mediaProperty.Review;
import com.codecool.crashbooks.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public void saveReview(Review review){
        reviewRepository.save(review);
    }
}
