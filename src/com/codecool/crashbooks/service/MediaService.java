package com.codecool.crashbooks.service;

import com.codecool.crashbooks.model.Media;
import com.codecool.crashbooks.model.mediaproperty.Author;
import com.codecool.crashbooks.model.mediaproperty.Category;
import com.codecool.crashbooks.model.mediaproperty.Genre;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

public class MediaService {

    public List<Media> getAllMedia(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        List<Media> mediaList = em.createNamedQuery("Media.getAllMedia", Media.class).getResultList();
        em.close();
        return mediaList;
    }

    public List<Media> getMediaBy(EntityManagerFactory emf, Genre genre) {
        EntityManager em = emf.createEntityManager();
        List<Media> mediaList = em.createNamedQuery("Media.getMediaByGenre", Media.class)
                .setParameter("id", genre.getId()).getResultList();
        em.close();
        return mediaList;
    }

    public List<Media> getMediaBy(EntityManagerFactory emf, Category category) {
        EntityManager em = emf.createEntityManager();
        List<Media> mediaList = em.createNamedQuery("Media.getMediaByCategory", Media.class)
                .setParameter("id", category.getId()).getResultList();
        em.close();
        return mediaList;
    }

    public List<Media> getMediaBy(EntityManagerFactory emf, Author author) {
        EntityManager em = emf.createEntityManager();
        List<Media> mediaList = em.createNamedQuery("Media.getMediaByAuthor", Media.class)
                .setParameter("id", author.getId()).getResultList();
        em.close();
        return mediaList;
    }

    public List<Media> getMediaBy(EntityManagerFactory emf, Genre genre, Category category) {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Media> cq = cb.createQuery(Media.class);

        Root<Media> mediaRoot = cq.from(Media.class);
        Join<Media, Genre> genreJoin = mediaRoot.join("genres");

        Predicate genreRestriction  = cb.equal(genreJoin.get("id"), genre.getId());
        Predicate categoryRestriction  = cb.equal(mediaRoot.get("category"), category.getId());

        cq.where(cb.and(genreRestriction, categoryRestriction));
        cq.orderBy(cb.asc(mediaRoot.get("title")));

        TypedQuery<Media> query = em.createQuery(cq);
        List<Media> mediaList = query.getResultList();

        em.close();
        return mediaList;
    }
}
