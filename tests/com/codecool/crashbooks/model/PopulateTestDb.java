package com.codecool.crashbooks.model;

import com.codecool.crashbooks.model.mediaproperty.*;
import com.codecool.crashbooks.utility.Password;

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

        Category book = new Category(CategoryType.BOOK);
        Category magazine = new Category(CategoryType.MAGAZINE);
        Category newspaper = new Category(CategoryType.NEWSPAPER);

        Genre comedy = new Genre(GenreType.COMEDY);
        Genre drama = new Genre(GenreType.DRAMA);
        Genre horror = new Genre(GenreType.HORROR);
        Genre romance = new Genre(GenreType.ROMANCE);
        Genre satire = new Genre(GenreType.SATIRE);
        Genre tragedy = new Genre(GenreType.TRAGEDY);
        Genre fantasy = new Genre(GenreType.FANTASY);
        Genre mythology = new Genre(GenreType.MYTHOLOGY);
        Genre tragicomedy = new Genre(GenreType.TRAGICOMEDY);
        Genre adventure = new Genre(GenreType.ADVENTURE);
        Genre realism = new Genre(GenreType.REALISM);

        Media media1 = new Media("testMedia1", testAuthor1, magazine, adventure, "url/pic.jpg", 1999, "A good book...");
        Media media2 = new Media("testMedia2", testAuthor1, book, mythology, "url/pic.jpg", 1919, "A good book...");
        Media media3 = new Media("testMedia3", testAuthor2, book, adventure, "url/pic.jpg", 1981, "A good book...");
        Media media4 = new Media("testMedia4", testAuthor3, newspaper, realism, "url/pic.jpg", 1965, "A good book...");

        Member testMember1 = new Member("test", Password.hashPassword("test"));
        Member testMember2 = new Member("test1", Password.hashPassword("test1"));

        Copy copy1 = new Copy(media1);
        Copy copy2 = new Copy();
        Copy copy3 = new Copy(media1);
        Copy copy4 = new Copy(media3);
        Copy copy5 = new Copy(media3);
        copy1.setStatus(StatusType.RENTED);
        copy2.setStatus(StatusType.AVAILABLE);
        copy3.setStatus(StatusType.PENDING);
        copy4.setStatus(StatusType.PENDING);

        Rent rent1 = new Rent(testMember1, copy1);
        rent1.setRentPeriod();

        Rent rent2 = new Rent(testMember1, copy3);
        rent2.setRentPeriod();
        rent2.setDateReturned();

        Rent rent3 = new Rent(testMember2, copy4);
        Rent rent4 = new Rent(testMember2, copy5);

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

        em.persist(testMember1);
        em.persist(testMember2);

        em.persist(copy1);
        em.persist(copy2);
        em.persist(copy3);
        em.persist(copy4);
        em.persist(copy5);

        em.persist(rent1);
        em.persist(rent2);
        em.persist(rent3);
        em.persist(rent4);

        transaction.commit();

        em.close();
    }
}
