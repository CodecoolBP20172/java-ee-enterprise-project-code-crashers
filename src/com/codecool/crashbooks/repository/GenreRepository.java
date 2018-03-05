package com.codecool.crashbooks.repository;

import com.codecool.crashbooks.model.mediaProperty.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
