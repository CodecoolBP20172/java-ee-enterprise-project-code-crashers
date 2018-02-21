package com.codecool.crashbooks.service;

import com.codecool.crashbooks.model.mediaproperty.Author;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.List;

public class AuthorService {

    public List<Author> getAllAuthor(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        List<Author> authorList = em.createNamedQuery("Author.getAllAuthor", Author.class).getResultList();
        em.close();
        return authorList;
    }
    public Author getAuthorById(EntityManagerFactory emf, int id){
        try {
            EntityManager em = emf.createEntityManager();
            Author author = em.createNamedQuery("Author.getById", Author.class)
                    .setParameter("id", id).getSingleResult();
            em.close();
            return author;
        }catch(NoResultException e){
            return null;
        }
    }

    public Author getAuthorByName(EntityManagerFactory emf, String name){
        try {
            EntityManager em = emf.createEntityManager();
            Author author = em.createNamedQuery("Author.getByName", Author.class)
                    .setParameter("name", name).getSingleResult();
            em.close();
            return author;
        }catch(NoResultException e){
            return null;
        }
    }
}
