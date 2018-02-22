package com.codecool.crashbooks.model;

import com.codecool.crashbooks.model.mediaproperty.Author;
import com.codecool.crashbooks.model.mediaproperty.Category;
import com.codecool.crashbooks.model.mediaproperty.Copy;
import com.codecool.crashbooks.model.mediaproperty.Genre;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "Media.getAllMedia", query = "SELECT m FROM Media m"),
        @NamedQuery(name = "Media.getMediaByGenre", query = "SELECT m FROM Media m JOIN m.genres bg " +
                " WHERE bg.id = :id"),
        @NamedQuery(name = "Media.getMediaByGenreAndCategory", query = "SELECT m FROM Media m JOIN m.genres bg " +
                " WHERE bg.id = :genreId AND category_id = :categoryId"),
        @NamedQuery(name = "Media.getMediaByCategory", query = "SELECT m FROM Media m WHERE category_id = :id"),
        @NamedQuery(name = "Media.getMediaByAuthor", query = "SELECT m FROM Media m WHERE author_id = :id")})
@Entity
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")

    private Author author;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "media_genres")
    private Set<Genre> genres = new HashSet<>();

    @OneToMany(mappedBy = "media", fetch = FetchType.LAZY)
    private List<Copy> copies = new ArrayList<>();
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


    public int getId() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }

    public Category getCategory() {
        return category;
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

    public void setGenres(Genre genres) {
        this.genres.add(genres);
    }
}


