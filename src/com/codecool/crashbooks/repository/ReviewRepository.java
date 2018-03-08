package com.codecool.crashbooks.repository;

import com.codecool.crashbooks.model.mediaProperty.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{
}
