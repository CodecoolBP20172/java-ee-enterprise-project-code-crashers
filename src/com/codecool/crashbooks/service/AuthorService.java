package com.codecool.crashbooks.service;

import com.codecool.crashbooks.model.mediaproperty.Author;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.List;

public class AuthorService {
    private EntityManagerFactory emf;

    public AuthorService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<Author> getAllAuthor() {
        EntityManager em = emf.createEntityManager();
        List<Author> authorList = em.createNamedQuery("Author.getAllAuthor", Author.class).getResultList();
        return authorList;
    }

    public Author getAuthorById(int id){
        EntityManager em = emf.createEntityManager();
        try {
            Author author = em.createNamedQuery("Author.getById", Author.class)
                    .setParameter("id", id).getSingleResult();
            return author;
        } catch(NoResultException e){
            return null;
        } finally {
            if(em != null) {
                em.close();
            }
        }
    }

    public Author getAuthorByName(String name){
        EntityManager em = emf.createEntityManager();
        try {
            Author author = em.createNamedQuery("Author.getByName", Author.class)
                    .setParameter("name", name).getSingleResult();
            return author;
        }catch(NoResultException e){
            return null;
        }finally {
            if(em != null) {
                em.close();
            }
        }
    }
}
