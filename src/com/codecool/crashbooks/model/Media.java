package com.codecool.crashbooks.model;

import com.codecool.crashbooks.model.mediaProperty.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "media", fetch = FetchType.EAGER)
    private Set<Copy> copies = new HashSet<>();

    @OneToMany(mappedBy = "media", fetch = FetchType.EAGER)
    private Set<Rating> ratings = new HashSet<>();

    @OneToMany(mappedBy = "media", fetch = FetchType.EAGER)
    private Set<Review> reviews = new HashSet<>();

    private String pictureUrl;
    private int year;

    @Lob
    @Column
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

    public static Media create(String title, Author author, Category category, Genre genre, String pictureUrl,
                               int year, String description) {
        return new Media(title, author, category, genre, pictureUrl, year, description);
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

    public long getAvailableCopiesNumber() {
        return copies.stream().filter(copy -> CopyStatuses.AVAILABLE.equals(copy.getStatus())).count();
    }

    public boolean isCopyAvailable() {
        return copies.stream().anyMatch(copy -> CopyStatuses.AVAILABLE.equals(copy.getStatus()));
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public int getYear() {
        return year;
    }

    public String getAverageRating() {
        if(ratings.size()==0){
            return "No rating yet!";
        }
        // returns a 1 decimal string
        return String.format("%.1f", ratings.stream().mapToDouble(o -> o.getStars()).sum()/ratings.size());
    }

    public int getCopiesCount() {
        return copies.size();
    }

    public Set<Copy> getCopies() {
        return copies;
    }
}


