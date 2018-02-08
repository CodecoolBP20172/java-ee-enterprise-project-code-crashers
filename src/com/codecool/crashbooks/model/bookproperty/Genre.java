package com.codecool.crashbooks.model.bookproperty;

import com.codecool.crashbooks.model.Media;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
@NamedQueries({
        @NamedQuery(name="Genre.getById", query="SELECT g FROM Genre g WHERE id = :id"),
        @NamedQuery(name="Genre.getByName", query="SELECT g FROM Genre g WHERE name = :name"),
        @NamedQuery(name = "Genre.getAllGenre", query="SELECT g FROM Genre g")
})
@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private Genres name;

    @ManyToMany(mappedBy = "genres")
    private Set<Media> book;

    public Genre(Genres name) {
        this.name = name;
    }

    public Genre() {
    }

    public int getId() {
        return id;
    }

    public Genres getName() {
        return name;
    }

    public static List<Genre> getAllGenre(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        List<Genre> genreList = em.createNamedQuery("Genre.getAllGenre", Genre.class).getResultList();
        em.close();
        return genreList;
    }
    public static Genre getGenreById(EntityManagerFactory emf, int id){
        EntityManager em = emf.createEntityManager();
        Genre genre = em.createNamedQuery("Genre.getById", Genre.class).setParameter("id", id).getSingleResult();
        em.close();
        return genre;
    }

    public static Genre getGenreByName(EntityManagerFactory emf, String name){
        EntityManager em = emf.createEntityManager();
        Genre genre = em.createNamedQuery("Genre.getByName", Genre.class).setParameter("name", name).getSingleResult();
        em.close();
        return genre;
    }
}
