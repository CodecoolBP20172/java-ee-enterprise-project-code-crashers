package com.codecool.crashbooks.service;

import com.codecool.crashbooks.model.mediaProperty.Rating;
import com.codecool.crashbooks.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public void saveRating(Rating rating) {
        ratingRepository.save(rating);
    }

    public Rating getRatingByMemberAndMedia(int memberId, int mediaId) {
        return ratingRepository.findByMember_IdAndMedia_Id(memberId, mediaId);
    }
}
