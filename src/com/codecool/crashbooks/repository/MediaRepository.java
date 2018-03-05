package com.codecool.crashbooks.repository;

import com.codecool.crashbooks.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Integer> {
}
