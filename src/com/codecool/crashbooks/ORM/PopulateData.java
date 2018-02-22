package com.codecool.crashbooks.ORM;

import com.codecool.crashbooks.model.Media;
import com.codecool.crashbooks.model.Member;
import com.codecool.crashbooks.model.mediaproperty.*;
import com.codecool.crashbooks.utility.Password;

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
        Author natgeo = new Author("National Geography");
        Author kepessport = new Author("Képes Sport");
        Author nemzetisport = new Author("Nemzeti Sport");
        Author nytimes = new Author("The New York Times");




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
        Genre science = new Genre(GenreType.SCIENCE);
        Genre sport = new Genre(GenreType.SPORT);


        Media vuk = new Media("Vuk", fekete, book, adventure, "vuk.jpg", 1965, "A good book...");
        Media tuskevar = new Media("Tüskevár", fekete, book, realism, "tuskevar.jpg", 1957, "A good book...");
        Media potter1 = new Media("Harry Potter and the Philosopher's Stone", rowling, book, fantasy, "harrypotter1.jpg", 1997, "A good book...");
        potter1.setGenres(adventure);
        Media potter2 = new Media("Harry Potter and the Chamber of Secrets", rowling, book, fantasy, "nobook.jpg", 1998, "A good book...");
        potter2.setGenres(adventure);
        Media lordOfTheRings1 = new Media("Lord of the Rings", tolkien, book, fantasy, "lordoftherings.jpg", 1954, "A good book...");
        Media gOfThrones1 = new Media("Game of Thrones", martin, book, fantasy, "gameofthrones.jpg", 1996, "A good book...");
        Media it = new Media("It", king, book, horror, "it.jpg", 1986, "A good book...");
        Media jurassicPark = new Media("Jurassic Park", crichton, book, fantasy, "jurassicpark.jpg", 1990, "A good book...");
        Media twister = new Media("Twister", crichton, book, drama, "twister.jpg", 1994, "A good book...");

        Media natgeo167 = new Media("Vol. 167/ No. 6", natgeo, magazine, science, "natgeo167-6.jpg", 1985, "A good magazine...");
        Media kepesSport = new Media("2017 Nyári különszám", kepessport, magazine, sport, "kepessport.jpg", 2017, "A good magazine...");

        Media nemzetiSport = new Media("2015.05.13", nemzetisport, newspaper, sport, "nemzetisport.jpg", 2015, "A good newspaper...");
        Media times = new Media("1969.07.21", nytimes, newspaper, science, "nytimes.jpg", 1969, "A good newspaper...");

        Member test = new Member("test", Password.hashPassword("test"));

        Copy copy1 = new Copy(it);
        Copy copy2 = new Copy(it);
        Copy copy3 = new Copy(lordOfTheRings1);
        copy2.setStatus(StatusType.PENDING);
        copy1.setStatus(StatusType.PENDING);

        Rent rent1 = new Rent(test, copy1);

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(fekete);
        em.persist(rowling);
        em.persist(tolkien);
        em.persist(martin);
        em.persist(king);
        em.persist(crichton);
        em.persist(natgeo);
        em.persist(kepessport);
        em.persist(nemzetisport);
        em.persist(nytimes);

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
        em.persist(sport);
        em.persist(science);


        em.persist(vuk);
        em.persist(tuskevar);
        em.persist(potter1);
        em.persist(potter2);
        em.persist(lordOfTheRings1);
        em.persist(gOfThrones1);
        em.persist(it);
        em.persist(jurassicPark);
        em.persist(twister);
        em.persist(natgeo167);
        em.persist(kepesSport);
        em.persist(nemzetiSport);
        em.persist(times);

        em.persist(test);

        em.persist(copy1);
        em.persist(copy2);
        em.persist(copy3);

        em.persist(rent1);

        transaction.commit();

        em.close();

    }

}
