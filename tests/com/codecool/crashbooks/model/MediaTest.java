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

    @Test
    @DisplayName("All media")
    public void testGetAllMedia(){
        assertEquals(4, (Media.getAllMedia(emf)).size());
    }

    @Test
    @DisplayName("Get Media by NULL Genre")
    public void testGetMediaByNullGenre() {
        Genre genre = null;
        assertThrows(NullPointerException.class, () -> {
                Media.getMediaBy(emf, genre).size();
        });
    }

    @Test
    @DisplayName("Get Media by Adventure Genre returns valid list size")
    public void testGetMediaByAdventureGenreValidListSize() {
        Genre genre = Genre.getGenreById(emf, 10);
        assertEquals(2, Media.getMediaBy(emf, genre).size());
    }

    @Test
    @DisplayName("Get Media by Mythology Genre returns valid list size")
    public void testGetMediaByMythologyGenreValidListSize() {
        Genre genre = Genre.getGenreById(emf, 9);
        assertEquals(1, Media.getMediaBy(emf, genre).size());
    }

    @Test
    @DisplayName("Get Media by Adventure Genre is valid Type")
    public void testGetMediaByAdventureGenreValidType() {
        Genre genre = Genre.getGenreById(emf, 10);
        System.out.println(Media.getMediaBy(emf, genre).get(0).getGenres());
        assertTrue(Media.getMediaBy(emf, genre).get(0)
                .getGenres()
                .contains(genre));
    }

    @Test
    @DisplayName("Get Media by Mythology Genre is valid Type")
    public void testGetMediaByMythologyGenreValidType() {
        Genre genre = Genre.getGenreById(emf, 9);
        System.out.println(Media.getMediaBy(emf, genre).get(0).getGenres());
        assertTrue(Media.getMediaBy(emf, genre).get(0)
                .getGenres()
                .contains(genre));
    }

    @Test
    @DisplayName("All book Media")
    public void testGetMediaByCategoryValidInputs() {
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