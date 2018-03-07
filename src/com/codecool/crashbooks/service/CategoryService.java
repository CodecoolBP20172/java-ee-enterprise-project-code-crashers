package com.codecool.crashbooks.service;

import com.codecool.crashbooks.model.mediaProperty.Category;
import com.codecool.crashbooks.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(int id) {
        return categoryRepository.findOne(id);
    }

    public List<Category> getCategoryByType(String type) {
        return categoryRepository.findByType(type);
    }

}
