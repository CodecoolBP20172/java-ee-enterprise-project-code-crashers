package com.codecool.crashbooks.service;

import com.codecool.crashbooks.model.Media;
import com.codecool.crashbooks.model.mediaProperty.Author;
import com.codecool.crashbooks.model.mediaProperty.Category;
import com.codecool.crashbooks.model.mediaProperty.Genre;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
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

    public List<Media> getMediasBy(Genre genre) {
        EntityManager em = emf.createEntityManager();
        List<Media> mediaList = em.createNamedQuery("Media.getMediaByGenre", Media.class)
                .setParameter("id", genre.getId()).getResultList();
        em.close();
        return mediaList;
    }

    public List<Media> getMediasBy(Category category) {
        EntityManager em = emf.createEntityManager();
        List<Media> mediaList = em.createNamedQuery("Media.getMediaByCategory", Media.class)
                .setParameter("id", category.getId()).getResultList();
        em.close();
        return mediaList;
    }

    public List<Media> getMediasBy(Author author) {
        EntityManager em = emf.createEntityManager();
        List<Media> mediaList = em.createNamedQuery("Media.getMediaByAuthor", Media.class)
                .setParameter("id", author.getId()).getResultList();
        em.close();
        return mediaList;
    }

    public List<Media> getMediasBy(Genre genre, Category category) {
        EntityManager em = emf.createEntityManager();
        List<Media> mediaList = em.createNamedQuery("Media.getMediaByGenreAndCategory", Media.class)
                .setParameter("genreId", genre.getId())
                .setParameter("categoryId", category.getId())
                .getResultList();
        em.close();
        return mediaList;
    }
}
