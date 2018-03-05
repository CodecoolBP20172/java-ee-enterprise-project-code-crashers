package com.codecool.crashbooks.repository;

import com.codecool.crashbooks.model.mediaProperty.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
