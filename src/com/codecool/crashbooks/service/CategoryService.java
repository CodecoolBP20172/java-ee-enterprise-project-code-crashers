package com.codecool.crashbooks.service;

import com.codecool.crashbooks.model.mediaproperty.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class CategoryService {

    public List<Category> getAllCategory(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        List<Category> categoryList = em.createNamedQuery("Category.getAllCategory", Category.class).getResultList();
        em.close();
        return categoryList;
    }

    public Category getCategoryById(EntityManagerFactory emf, int id){
        EntityManager em = emf.createEntityManager();
        Category category = em.createNamedQuery("Category.getById", Category.class).setParameter("id", id).getSingleResult();
        em.close();
        return category;
    }

    public Category getCategoryByType(EntityManagerFactory emf, String type){
        EntityManager em = emf.createEntityManager();
        Category category = em.createNamedQuery("Category.getByType", Category.class).setParameter("type", type).getSingleResult();
        em.close();
        return category;
    }

}
