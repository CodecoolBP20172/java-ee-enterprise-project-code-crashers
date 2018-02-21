package com.codecool.crashbooks.service;

import com.codecool.crashbooks.model.mediaproperty.Genre;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class GenreService {

    public List<Genre> getAllGenre(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        List<Genre> genreList = em.createNamedQuery("Genre.getAllGenre", Genre.class).getResultList();
        em.close();
        return genreList;
    }
    public Genre getGenreById(EntityManagerFactory emf, int id){
        EntityManager em = emf.createEntityManager();
        Genre genre = em.createNamedQuery("Genre.getById", Genre.class).setParameter("id", id).getSingleResult();
        em.close();
        return genre;
    }

    public Genre getGenreByType(EntityManagerFactory emf, String type){
        EntityManager em = emf.createEntityManager();
        Genre genre = em.createNamedQuery("Genre.getByType", Genre.class).setParameter("type", type).getSingleResult();
        em.close();
        return genre;
    }
}
