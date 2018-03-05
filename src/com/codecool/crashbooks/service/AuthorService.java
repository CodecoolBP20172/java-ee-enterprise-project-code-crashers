package com.codecool.crashbooks.service;

import com.codecool.crashbooks.model.mediaProperty.Author;
import com.codecool.crashbooks.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    /*private final EntityManagerFactory emf;

    public AuthorService(EntityManagerFactory emf) {
        this.emf = emf;
    }*/

    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }

    public List<Author> getAllAuthor() {
        //EntityManager em = emf.createEntityManager();
        //return em.createNamedQuery("Author.getAllAuthor", Author.class).getResultList();
        return authorRepository.findAll();
    }

    public Author getAuthorById(int id) {
        /*EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Author.getById", Author.class)
                    .setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }*/
        return authorRepository.findOne(id);
    }

    public List<Author> getAuthorByName(String name) {
        /*EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Author.getByName", Author.class)
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }*/
        return authorRepository.findByName(name);
    }
}
