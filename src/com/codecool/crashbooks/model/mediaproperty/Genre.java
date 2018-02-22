package com.codecool.crashbooks.model.mediaproperty;

import com.codecool.crashbooks.model.Media;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;
@NamedQueries({
        @NamedQuery(name="Genre.getById", query="SELECT g FROM Genre g WHERE id = :id"),
        @NamedQuery(name="Genre.getByType", query="SELECT g FROM Genre g WHERE type = :type"),
        @NamedQuery(name = "Genre.getAllGenre", query="SELECT g FROM Genre g")
})
@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private GenreType type;

    @ManyToMany(mappedBy = "genres", fetch = FetchType.EAGER)
    private Set<Media> media;

    public Genre(GenreType type) {
        this.type = type;
    }

    public Genre() {
    }

    public int getId() {
        return id;
    }

    public GenreType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return id == genre.id &&
                type == genre.type;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type);
    }
}
