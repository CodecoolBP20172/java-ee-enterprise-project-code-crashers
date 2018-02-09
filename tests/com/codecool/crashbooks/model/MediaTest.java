package com.codecool.crashbooks.model;

import com.codecool.crashbooks.model.bookproperty.Author;
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

    //Genre tests
    @Test
    @DisplayName("Get Media by NULL Genre")
    public void testGetMediaByGenreForInvalidGenre() {
        Genre genre = null;
        assertThrows(NullPointerException.class, () -> {
            Media.getMediaBy(emf, genre).size();
        });
    }

    @Test
    @DisplayName("Size check for allMediaByGenre, ADVENTURE")
    public void testGetMediaByGenreValidSizeForAdventure() {
        Genre genre = Genre.getGenreById(emf, 10);
        assertEquals(2, Media.getMediaBy(emf, genre).size());
    }

    @Test
    @DisplayName("Size check for allMediaByGenre, MYTHOLOGY")
    public void testGetMediaByGenreValidSizeForMythology() {
        Genre genre = Genre.getGenreById(emf, 9);
        assertEquals(1, Media.getMediaBy(emf, genre).size());
    }

    @Test
    @DisplayName("Check for valid type, ADVENTURE")
    public void testGetMediaByAdventureGenreValidType() {
        Genre genre = Genre.getGenreById(emf, 10);
        assertTrue(Media.getMediaBy(emf, genre).get(0)
                .getGenres()
                .contains(genre));
    }

    @Test
    @DisplayName("Check for valid type, MYTHOLOGY")
    public void testGetMediaByMythologyGenreValidType() {
        Genre genre = Genre.getGenreById(emf, 9);
        assertTrue(Media.getMediaBy(emf, genre).get(0)
                .getGenres()
                .contains(genre));
    }

    //Category tests
    @Test
    @DisplayName("Size check for allMediaByCategory, BOOK")
    public void testGetAllMediaByCategoryValidSizeForBooks() {
        Category category = Category.getCategoryById(emf, 1);
        assertEquals(2, Media.getMediaBy(emf, category).size());
    }

    @Test
    @DisplayName("Size check for allMediaByCategory, MAGAZINE")
    public void testGetAllMediaByCategoryValidSizeForMagazines() {
        Category category = Category.getCategoryById(emf, 2);
        assertEquals(1, Media.getMediaBy(emf, category).size());
    }

    @Test
    @DisplayName("Check for invalid category")
    public void testGetAllMediaByCategoryForInvalidCategory() {
        Category category = null;
        assertThrows(NullPointerException.class, () -> {
            Media.getMediaBy(emf, category);
        });
    }

    @Test
    @DisplayName("Check for valid type, MAGAZINE")
    public void testGetAllMediaByCategoryForMagazineType() {
        Category exceptedCategory = Category.getCategoryById(emf, 2);
        Category realCategory = Media.getAllMedia(emf).get(0).getCategory();
        assertEquals(exceptedCategory.getName(), realCategory.getName());
    }

    @Test
    @DisplayName("Check for valid type, Book")
    public void testGetAllMediaByCategoryForBookType() {
        Category exceptedCategory = Category.getCategoryById(emf, 1);
        Category realCategory = Media.getAllMedia(emf).get(1).getCategory();
        assertEquals(exceptedCategory.getName(), realCategory.getName());
    }

    //Author tests
    @Test
    @DisplayName("Size check for allMediaByaUTHOR, testAuthor1")
    public void testGetAllMediaByCategoryForTestAuthor1() {
        Author author = Author.getAuthorById(emf, 1);
        assertEquals(2, Media.getMediaBy(emf, author).size());
    }
}
