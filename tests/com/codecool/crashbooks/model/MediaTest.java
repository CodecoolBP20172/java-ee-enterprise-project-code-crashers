package com.codecool.crashbooks.model;

import com.codecool.crashbooks.model.mediaproperty.Author;
import com.codecool.crashbooks.model.mediaproperty.Category;
import com.codecool.crashbooks.model.mediaproperty.Genre;
import com.codecool.crashbooks.service.AuthorService;
import com.codecool.crashbooks.service.CategoryService;
import com.codecool.crashbooks.service.GenreService;
import com.codecool.crashbooks.service.MediaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MediaTest extends SetupAndTearDown {
    MediaService mediaService = new MediaService();
    GenreService genreService = new GenreService();
    CategoryService categoryService = new CategoryService();
    AuthorService authorService = new AuthorService();

    @Test
    @DisplayName("All media")
    public void testGetAllMedia() {
        assertEquals(4, (mediaService.getAllMedia(emf)).size());
    }

    //Genre tests
    @Test
    @DisplayName("Get Media by NULL Genre")
    public void testGetMediaByGenreForInvalidGenre() {
        Genre genre = null;
        assertThrows(NullPointerException.class, () -> {
            mediaService.getMediaBy(emf, genre).size();
        });
    }

    @Test
    @DisplayName("Size check for allMediaByGenre, ADVENTURE")
    public void testGetMediaByGenreValidSizeForAdventure() {
        Genre genre = genreService.getGenreById(emf, 10);
        assertEquals(2, mediaService.getMediaBy(emf, genre).size());
    }

    @Test
    @DisplayName("Size check for allMediaByGenre, MYTHOLOGY")
    public void testGetMediaByGenreValidSizeForMythology() {
        Genre genre = genreService.getGenreById(emf, 9);
        assertEquals(1, mediaService.getMediaBy(emf, genre).size());
    }

    @Test
    @DisplayName("Check for valid type, ADVENTURE")
    public void testGetMediaByAdventureGenreValidType() {
        Genre genre = genreService.getGenreById(emf, 10);
        assertTrue(mediaService.getMediaBy(emf, genre).get(0)
                .getGenres()
                .contains(genre));
    }

    @Test
    @DisplayName("Check for valid type, MYTHOLOGY")
    public void testGetMediaByMythologyGenreValidType() {
        Genre genre = genreService.getGenreById(emf, 9);
        assertTrue(mediaService.getMediaBy(emf, genre).get(0)
                .getGenres()
                .contains(genre));
    }

    //Category tests
    @Test
    @DisplayName("Size check for allMediaByCategory, BOOK")
    public void testGetAllMediaByCategoryValidSizeForBooks() {
        Category category = categoryService.getCategoryById(emf, 1);
        assertEquals(2, mediaService.getMediaBy(emf, category).size());
    }

    @Test
    @DisplayName("Size check for allMediaByCategory, MAGAZINE")
    public void testGetAllMediaByCategoryValidSizeForMagazines() {
        Category category = categoryService.getCategoryById(emf, 2);
        assertEquals(1, mediaService.getMediaBy(emf, category).size());
    }

    @Test
    @DisplayName("Check for invalid category")
    public void testGetAllMediaByCategoryForInvalidCategory() {
        Category category = null;
        assertThrows(NullPointerException.class, () -> {
            mediaService.getMediaBy(emf, category);
        });
    }

    @Test
    @DisplayName("Check for valid type, MAGAZINE")
    public void testGetAllMediaByCategoryForMagazineType() {
        Category exceptedCategory = categoryService.getCategoryById(emf, 2);
        Category realCategory = mediaService.getAllMedia(emf).get(0).getCategory();
        assertEquals(exceptedCategory.getType(), realCategory.getType());
    }

    @Test
    @DisplayName("Check for valid type, Book")
    public void testGetAllMediaByCategoryForBookType() {
        Category exceptedCategory = categoryService.getCategoryById(emf, 1);
        Category realCategory = mediaService.getAllMedia(emf).get(1).getCategory();
        assertEquals(exceptedCategory.getType(), realCategory.getType());
    }

    //Author tests
    @Test
    @DisplayName("Size check for allMediaByaUTHOR, testAuthor1")
    public void testGetAllMediaByCategoryForTestAuthor1() {
        Author author = authorService.getAuthorById(emf, 1);
        assertEquals(2, mediaService.getMediaBy(emf, author).size());
    }
}
