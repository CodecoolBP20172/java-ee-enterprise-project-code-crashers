package com.codecool.crashbooks.model;

import com.codecool.crashbooks.model.bookproperty.Category;
import com.codecool.crashbooks.model.bookproperty.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MediaTest extends SetupAndTearDown {

    @Test
    @DisplayName("All media")
    public void testGetAllMedia() {
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
    @DisplayName("Size check for allMediaByCategory, BOOK")
    public void testGetAllMediaByCategoryValidSizeForBooks() {
        Category category = Category.getCategoryById(emf, 1);
        assertEquals(2, Media.getMediaBy(emf, category).size());
    }

    @Test
    @DisplayName("Size check for allMediaByCategory, MAGAZINE")
    public void testGetAllMediaByCategorzValidSizeForMagazines() {
        Category category = Category.getCategoryById(emf, 2);
        assertEquals(1, Media.getMediaBy(emf, category).size());
    }

    @Test
    @DisplayName("Check for invalid category")
    public void testGetAllMediaByCategory() {
        Category category = null;
        assertThrows(NullPointerException.class, () -> {
            Media.getMediaBy(emf, category);
        });
    }

}
