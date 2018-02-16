package com.codecool.crashbooks.ORM;

import com.codecool.crashbooks.model.Media;
import com.codecool.crashbooks.model.Member;
import com.codecool.crashbooks.model.bookproperty.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class PopulateData {

    public static void populateDB(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        Author fekete = new Author("Fekete István");
        Author rowling = new Author("J. K. Rowling");
        Author tolkien = new Author("J. R. R. Tolkien");
        Author martin = new Author("George R. R. Martin");
        Author king = new Author("Stephen King");
        Author crichton = new Author("Michael Crichton");


        Category book = new Category(Categories.BOOK);
        Category magazine = new Category(Categories.MAGAZINE);
        Category newspaper = new Category(Categories.NEWSPAPER);

        Genre comedy = new Genre(Genres.COMEDY);
        Genre drama = new Genre(Genres.DRAMA);
        Genre horror = new Genre(Genres.HORROR);
        Genre romance = new Genre(Genres.ROMANCE);
        Genre satire = new Genre(Genres.SATIRE);
        Genre tragedy = new Genre(Genres.TRAGEDY);
        Genre fantasy = new Genre(Genres.FANTASY);
        Genre mythology = new Genre(Genres.MYTHOLOGY);
        Genre tragicomedy = new Genre(Genres.TRAGICOMEDY);
        Genre adventure = new Genre(Genres.ADVENTURE);
        Genre realism = new Genre(Genres.REALISM);

        Media vuk = new Media("Vuk", fekete, book, adventure, "book_thumb.png", 1965, "A good book...");
        Media tuskevar = new Media("Tüskevár", fekete, book, realism, "book_thumb.png", 1957, "A good book...");
        Media potter1 = new Media("Harry Potter", rowling, book, fantasy, "book_thumb.png", 1997, "A good book...");
        potter1.setGenres(adventure);
        Media lordOfTheRings1 = new Media("Lord of the Rings", tolkien, book, fantasy, "book_thumb.png", 1954, "A good book...");
        Media gOfThrones1 = new Media("Game of Thrones", martin, book, fantasy, "book_thumb.png", 1996, "A good book...");
        Media it = new Media("It", king, book, horror, "book_thumb.png", 1986, "A good book...");
        Media jurassicPark = new Media("Jurassic Park", crichton, book, fantasy, "book_thumb.png", 1990, "A good book...");
        Media twister = new Media("Twister", crichton, book, drama, "book_thumb.png", 1994, "A good book...");

        Member test = new Member("test", "test");
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(fekete);
        em.persist(rowling);
        em.persist(tolkien);
        em.persist(martin);
        em.persist(king);
        em.persist(crichton);

        em.persist(book);
        em.persist(magazine);
        em.persist(newspaper);

        em.persist(comedy);
        em.persist(drama);
        em.persist(horror);
        em.persist(romance);
        em.persist(satire);
        em.persist(tragedy);
        em.persist(tragicomedy);
        em.persist(fantasy);
        em.persist(mythology);
        em.persist(adventure);
        em.persist(realism);

        em.persist(vuk);
        em.persist(tuskevar);
        em.persist(potter1);
        em.persist(lordOfTheRings1);
        em.persist(gOfThrones1);
        em.persist(it);
        em.persist(jurassicPark);
        em.persist(twister);

        em.persist(test);

        transaction.commit();

        em.close();

    }

}
