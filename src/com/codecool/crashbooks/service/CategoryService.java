package com.codecool.crashbooks.service;

import com.codecool.crashbooks.model.mediaProperty.Category;
import com.codecool.crashbooks.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /*private final EntityManagerFactory emf;

    public CategoryService(EntityManagerFactory emf) {
        this.emf = emf;
    }*/

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    public List<Category> getAllCategory() {
        /*EntityManager em = emf.createEntityManager();
        List<Category> categoryList = em.createNamedQuery("Category.getAllCategory", Category.class).getResultList();
        em.close();
        return categoryList;*/
        return categoryRepository.findAll();
    }

    public Category getCategoryById(int id) {
        /*EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Category.getById", Category.class)
                    .setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }*/
        return categoryRepository.findOne(id);
    }

    public List<Category> getCategoryByType(String type) {
        /*EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Category.getByType", Category.class)
                    .setParameter("type", type).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }*/
        return categoryRepository.findByType(type);
    }

}
