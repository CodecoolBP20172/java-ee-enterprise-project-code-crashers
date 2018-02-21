package com.codecool.crashbooks.service;

import com.codecool.crashbooks.model.mediaproperty.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.List;

public class CategoryService {
    EntityManagerFactory emf;

    public CategoryService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<Category> getAllCategory() {
        EntityManager em = emf.createEntityManager();
        List<Category> categoryList = em.createNamedQuery("Category.getAllCategory", Category.class).getResultList();
        em.close();
        return categoryList;
    }

    public Category getCategoryById(int id){
        EntityManager em = emf.createEntityManager();
        try {
            Category category = em.createNamedQuery("Category.getById", Category.class)
                    .setParameter("id", id).getSingleResult();
            return category;
        }catch(NoResultException e){
            return null;
        } finally {
            if(em != null) {
                em.close();
            }
        }
    }

    public Category getCategoryByType(String type){
        EntityManager em = emf.createEntityManager();
        try {
            Category category = em.createNamedQuery("Category.getByType", Category.class)
                    .setParameter("type", type).getSingleResult();
            return category;
        } catch(NoResultException e){
            return null;
        } finally {
            if(em != null) {
                em.close();
            }
        }
    }

}
