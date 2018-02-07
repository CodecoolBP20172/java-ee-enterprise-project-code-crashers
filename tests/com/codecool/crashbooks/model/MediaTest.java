package com.codecool.crashbooks.model;

import com.codecool.crashbooks.model.bookproperty.*;
import org.junit.Ignore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MediaTest extends SetupAndTearDown{
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("CrashBooks");

    @Test
    @DisplayName("All media")
    public void testGetAllMedia(){  // TODO assertEqual list.size?
        List<Media> expectedResultList = new ArrayList<Media>();
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

        Media vuk = new Media("Vuk", fekete, book, adventure, "url/pic.jpg", 1965, "A good book...");
        Media tuskevar = new Media("Tüskevár", fekete, book, realism, "url/pic.jpg", 1957, "A good book...");
        Media potter1 = new Media("Harry Potter", rowling, book, fantasy, "url/pic.jpg", 1997, "A good book...");
        potter1.setGenres(adventure);
        Media lordOfTheRings1 = new Media("Lord of the Rings", tolkien, book, fantasy, "url/pic.jpg", 1954, "A good book...");
        Media gOfThrones1 = new Media("Game of Thrones", martin, book, fantasy, "url/pic.jpg", 1996, "A good book...");
        Media it = new Media("It", king, book, horror, "url/pic.jpg", 1986, "A good book...");
        Media jurassicPark = new Media("Jurassic Park", crichton, book, fantasy, "url/pic.jpg", 1990, "A good book...");
        Media twister = new Media("Twister", crichton, book, drama, "url/pic.jpg", 1994, "A good book...");
        expectedResultList.add(vuk);
        expectedResultList.add(tuskevar);
        expectedResultList.add(potter1);
        expectedResultList.add(lordOfTheRings1);
        expectedResultList.add(gOfThrones1);
        expectedResultList.add(it);
        expectedResultList.add(jurassicPark);
        expectedResultList.add(twister);
        assertEquals(expectedResultList.toString(), Media.getAllMedia(emf));
    }

    @Ignore
    @Test
    @DisplayName("All Fantasy Media")
    public void testGetMediaByGenreValid() {
        Genre genre = new Genre(Genres.FANTASY);
        assertEquals(4, (Media.getMediaBy(emf, genre)).size());
    }

    @Test
    @DisplayName("All book Media")
    public void testGetMediaByCategValidInputs() {
        Category book = new Category(Categories.BOOK);
        assertEquals(8, (Media.getMediaBy(emf, book)).size());
    }

    @Test
    @DisplayName("Media by Cagetory null")
    public void testGetMediaByCategInValidInputs() {
        Category book = null;
        assertThrows(NullPointerException.class, () -> {
            Media.getMediaBy(emf, book);
        });
    }
}