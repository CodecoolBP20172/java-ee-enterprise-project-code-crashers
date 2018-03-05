package com.codecool.crashbooks.repository;

import com.codecool.crashbooks.model.mediaProperty.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findByType(String type);
}
