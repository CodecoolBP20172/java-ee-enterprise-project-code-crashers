package com.codecool.crashbooks.model.bookproperty;

import com.codecool.crashbooks.model.Media;

import javax.persistence.*;
import java.util.Set;
@NamedQueries({
        @NamedQuery(name="Genre.getById", query="SELECT g FROM Genre g WHERE id = :id")
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
    public static Genre getGenreById(EntityManagerFactory emf, int id){
        EntityManager em = emf.createEntityManager();
        Genre genre = em.createNamedQuery("Genre.getById", Genre.class).setParameter("id", id).getSingleResult();
        em.close();
        return genre;
    }
}
