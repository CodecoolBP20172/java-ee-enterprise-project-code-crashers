package com.codecool.crashbooks.repository;

import com.codecool.crashbooks.model.mediaProperty.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
