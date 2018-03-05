package com.codecool.crashbooks.service;

import com.codecool.crashbooks.model.mediaProperty.Genre;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.List;

public class GenreService {
    private final EntityManagerFactory emf;

    public GenreService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<Genre> getAllGenre() {
        EntityManager em = emf.createEntityManager();
        List<Genre> genreList = em.createNamedQuery("Genre.getAllGenre", Genre.class).getResultList();
        em.close();
        return genreList;
    }

    public Genre getGenreById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Genre.getById", Genre.class)
                    .setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Genre getGenreByType(EntityManagerFactory emf, String type) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Genre.getByType", Genre.class)
                    .setParameter("type", type).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
