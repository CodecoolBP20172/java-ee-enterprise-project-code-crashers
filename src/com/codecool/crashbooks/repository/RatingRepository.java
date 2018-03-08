package com.codecool.crashbooks.repository;

import com.codecool.crashbooks.model.mediaProperty.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
}
