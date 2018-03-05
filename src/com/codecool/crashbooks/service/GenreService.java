package com.codecool.crashbooks.service;

import com.codecool.crashbooks.model.mediaProperty.Genre;
import com.codecool.crashbooks.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    /*private final EntityManagerFactory emf;

    public GenreService(EntityManagerFactory emf) {
        this.emf = emf;
    }*/
    public void saveGenre(Genre genre) {
        genreRepository.save(genre);
    }

    public List<Genre> getAllGenre() {
        /*EntityManager em = emf.createEntityManager();
        List<Genre> genreList = em.createNamedQuery("Genre.getAllGenre", Genre.class).getResultList();
        em.close();
        return genreList;*/
        return genreRepository.findAll();
    }

    public Genre getGenreById(int id) {
        /*EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Genre.getById", Genre.class)
                    .setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }*/
        return genreRepository.findOne(id);
    }

    public List<Genre> getGenreByType(String type) {
        /*EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Genre.getByType", Genre.class)
                    .setParameter("type", type).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }*/
        return genreRepository.findByType(type);
    }
}
