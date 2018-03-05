package com.codecool.crashbooks.model;

import com.codecool.crashbooks.model.mediaProperty.Author;
import com.codecool.crashbooks.model.mediaProperty.Category;
import com.codecool.crashbooks.model.mediaProperty.Categories;
import com.codecool.crashbooks.model.mediaProperty.Genre;
import com.codecool.crashbooks.service.AuthorService;
import com.codecool.crashbooks.service.CategoryService;
import com.codecool.crashbooks.service.GenreService;
import com.codecool.crashbooks.service.MediaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class MediaTest extends SetupAndTearDown {
    MediaService mediaService = new MediaService(emf);
    GenreService genreService = new GenreService(emf);
    CategoryService categoryService = new CategoryService(emf);
    AuthorService authorService = new AuthorService(emf);

    @Test
    @DisplayName("All media")
    public void testGetAllMedia() {
        assertEquals(4, (mediaService.getAllMedia()).size());
    }

    //Genre tests
    @Test
    @DisplayName("Get Media by NULL Genre")
    public void testGetMediaByGenreForInvalidGenre() {
        Genre genre = null;
        assertThrows(NullPointerException.class, () -> {
            mediaService.getMediasBy(genre).size();
        });
    }

    @Test
    @DisplayName("Size check for allMediaByGenre, ADVENTURE")
    public void testGetMediaByGenreValidSizeForAdventure() {
        Genre genreMock = Mockito.mock(Genre.class);
        Mockito.when(genreMock.getId()).thenReturn(10);
        assertEquals(2, mediaService.getMediasBy(genreMock).size());
    }

    @Test
    @DisplayName("Size check for allMediaByGenre, MYTHOLOGY")
    public void testGetMediaByGenreValidSizeForMythology() {
        Genre genreMock = Mockito.mock(Genre.class);
        Mockito.when(genreMock.getId()).thenReturn(9);
        assertEquals(1, mediaService.getMediasBy(genreMock).size());
    }

    @Test
    @DisplayName("Check for valid type, ADVENTURE")
    public void testGetMediaByAdventureGenreValidType() {
        Genre genre = genreService.getGenreById(10);
        assertTrue(mediaService.getMediasBy(genre).get(0)
                .getGenres()
                .contains(genre));  //TODO rethink to use mock
    }

    @Test
    @DisplayName("Check for valid type, MYTHOLOGY")
    public void testGetMediaByMythologyGenreValidType() {
        Genre genre = genreService.getGenreById(9);
        assertTrue(mediaService.getMediasBy(genre).get(0)
                .getGenres()
                .contains(genre)); //TODO rethink to use mock
    }

    //Category tests
    @Test
    @DisplayName("Size check for allMediaByCategory, BOOK")
    public void testGetAllMediaByCategoryValidSizeForBooks() {
        Category categoryMock = Mockito.mock(Category.class);
        Mockito.when(categoryMock.getId()).thenReturn(1);
        assertEquals(2, mediaService.getMediasBy(categoryMock).size());
    }

    @Test
    @DisplayName("Size check for allMediaByCategory, MAGAZINE")
    public void testGetAllMediaByCategoryValidSizeForMagazines() {
        Category categoryMock = Mockito.mock(Category.class);
        Mockito.when(categoryMock.getId()).thenReturn(2);
        assertEquals(1, mediaService.getMediasBy(categoryMock).size());
    }

    @Test
    @DisplayName("Check for invalid category")
    public void testGetAllMediaByCategoryForInvalidCategory() {
        Category category = null;
        assertThrows(NullPointerException.class, () -> {
            mediaService.getMediasBy(category);
        });
    }

    @Test
    @DisplayName("Check for valid type, MAGAZINE")
    public void testGetAllMediaByCategoryForMagazineType() {
        Category realCategory = mediaService.getAllMedia().get(0).getCategory();
        assertEquals(Categories.MAGAZINE, realCategory.getType());
    }

    @Test
    @DisplayName("Check for valid type, Book")
    public void testGetAllMediaByCategoryForBookType() {
        Category realCategory = mediaService.getAllMedia().get(1).getCategory();
        assertEquals(Categories.BOOK, realCategory.getType());
    }

    //Author tests
    @Test
    @DisplayName("Size check for allMediaByAuthor, testAuthor1")
    public void testGetAllMediaByCategoryForTestAuthor1() {
        Author authorMock = Mockito.mock(Author.class);
        Mockito.when(authorMock.getId()).thenReturn(1);
        assertEquals(2, mediaService.getMediasBy(authorMock).size());
    }
}
