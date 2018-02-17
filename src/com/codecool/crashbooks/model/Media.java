package com.codecool.crashbooks.model;

import com.codecool.crashbooks.model.mediaproperty.Author;
import com.codecool.crashbooks.model.mediaproperty.Category;
import com.codecool.crashbooks.model.mediaproperty.Genre;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "Media.getAllMedia", query = "SELECT m FROM Media m"),
        @NamedQuery(name = "Media.getMediaByGenre", query = "SELECT m FROM Media m JOIN m.genres bg " +
                " WHERE bg.id = :id"),
        @NamedQuery(name = "Media.getMediaByCategory", query = "SELECT m FROM Media m WHERE category_id = :id"),
        @NamedQuery(name = "Media.getMediaByAuthor", query = "SELECT m FROM Media m WHERE author_id = :id")})
@Entity
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_genres")
    private Set<Genre> genres = new HashSet<>();
    private String pictureUrl;
    private int year;
    private String description;

    public Media() {
    }

    public Media(String title, Author author, Category category, Genre genre, String pictureUrl, int year, String description) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.genres.add(genre);
        this.pictureUrl = pictureUrl;
        this.year = year;
        this.description = description;
    }

    //test constructor can be deleted
    public Media(String title, String description) {
        this.title = title;
        this.description = description;
        this.pictureUrl = "book_thumb.png";
    }

    public static List<Media> getAllMedia(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        List<Media> mediaList = em.createNamedQuery("Media.getAllMedia", Media.class).getResultList();
        em.close();
        return mediaList;
    }

    public static List<Media> getMediaBy(EntityManagerFactory emf, Genre genre) {
        EntityManager em = emf.createEntityManager();
        List<Media> mediaList = em.createNamedQuery("Media.getMediaByGenre", Media.class).setParameter("id", genre.getId()).getResultList();
        em.close();
        return mediaList;
    }

    public static List<Media> getMediaBy(EntityManagerFactory emf, Category category) {
        EntityManager em = emf.createEntityManager();
        List<Media> mediaList = em.createNamedQuery("Media.getMediaByCategory", Media.class).setParameter("id", category.getId()).getResultList();
        em.close();
        return mediaList;
    }

    public static List<Media> getMediaBy(EntityManagerFactory emf, Author author) {
        EntityManager em = emf.createEntityManager();
        List<Media> mediaList = em.createNamedQuery("Media.getMediaByAuthor", Media.class).setParameter("id", author.getId()).getResultList();
        em.close();
        return mediaList;
    }

    public static List<Media> getMediaBy(EntityManagerFactory emf, Genre genre, Category category) {
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

    public Category getCategory() {
        return category;
    }

    public void setGenres(Genre genres) {
        this.genres.add(genres);
    }

    public String getTitle() {
        return title;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public String getDescription() {
        return description;
    }

    public Set<Genre> getGenres() {
        return genres;
    }
}


