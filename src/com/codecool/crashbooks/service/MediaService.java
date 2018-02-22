package com.codecool.crashbooks.service;

import com.codecool.crashbooks.model.Media;
import com.codecool.crashbooks.model.mediaproperty.Author;
import com.codecool.crashbooks.model.mediaproperty.Category;
import com.codecool.crashbooks.model.mediaproperty.Genre;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class MediaService {
    private final EntityManagerFactory emf;

    public MediaService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<Media> getAllMedia() {
        EntityManager em = emf.createEntityManager();
        List<Media> mediaList = em.createNamedQuery("Media.getAllMedia", Media.class).getResultList();
        em.close();
        return mediaList;
    }

    public List<Media> getMediaBy(Genre genre) {
        EntityManager em = emf.createEntityManager();
        List<Media> mediaList = em.createNamedQuery("Media.getMediaByGenre", Media.class)
                .setParameter("id", genre.getId()).getResultList();
        em.close();
        return mediaList;
    }

    public List<Media> getMediaBy(Category category) {
        EntityManager em = emf.createEntityManager();
        List<Media> mediaList = em.createNamedQuery("Media.getMediaByCategory", Media.class)
                .setParameter("id", category.getId()).getResultList();
        em.close();
        return mediaList;
    }

    public List<Media> getMediaBy(Author author) {
        EntityManager em = emf.createEntityManager();
        List<Media> mediaList = em.createNamedQuery("Media.getMediaByAuthor", Media.class)
                .setParameter("id", author.getId()).getResultList();
        em.close();
        return mediaList;
    }

    public List<Media> getMediaBy(Genre genre, Category category) {
        EntityManager em = emf.createEntityManager();
        List<Media> mediaList = em.createNamedQuery("Media.getMediaByGenreAndCategory", Media.class)
                .setParameter("genreId", genre.getId())
                .setParameter("categoryId", category.getId())
                .getResultList();
        em.close();
        return mediaList;
    }
}
