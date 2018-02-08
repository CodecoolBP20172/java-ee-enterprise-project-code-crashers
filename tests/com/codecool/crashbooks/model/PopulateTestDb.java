package com.codecool.crashbooks.model;

import com.codecool.crashbooks.model.bookproperty.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class PopulateTestDb {

    public void populateTestDB(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        Author testAuthor1 = new Author("testAuthor1");
        Author testAuthor2 = new Author("testAuthor2");
        Author testAuthor3 = new Author("testAuthor3");
        Author testAuthor4 = new Author("testAuthor4");

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

        Media media1 = new Media("testMedia1", testAuthor1, magazine, adventure, "url/pic.jpg", 1999, "A good book...");
        Media media2 = new Media("testMedia2", testAuthor1, book, mythology, "url/pic.jpg", 1919, "A good book...");
        Media media3 = new Media("testMedia3", testAuthor2, book, adventure, "url/pic.jpg", 1981, "A good book...");
        Media media4 = new Media("testMedia4", testAuthor3, newspaper, realism, "url/pic.jpg", 1965, "A good book...");

        AllUsers testUser = new AllUsers("test1", "test1");
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(testAuthor1);
        em.persist(testAuthor2);
        em.persist(testAuthor3);
        em.persist(testAuthor4);

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

        em.persist(media1);
        em.persist(media2);
        em.persist(media3);
        em.persist(media4);

        em.persist(testUser);

        transaction.commit();

        em.close();
    }
}
