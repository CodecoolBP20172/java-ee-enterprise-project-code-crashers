package com.codecool.crashbooks.model.bookproperty;

import com.codecool.crashbooks.model.Media;

import javax.persistence.*;
import java.util.List;
@NamedQueries({
        @NamedQuery(name="Author.getById", query="SELECT a FROM Author a WHERE id = :id")
})
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Media> book;

    public Author(String name) {
        this.name = name;
    }

    public Author() {
    }

    public int getId() {
        return id;
    }

    public static Author getAuthorById(EntityManagerFactory emf, int id){
        EntityManager em = emf.createEntityManager();
        Author author = em.createNamedQuery("Author.getById", Author.class).setParameter("id", id).getSingleResult();
        em.close();
        return author;
    }
}
