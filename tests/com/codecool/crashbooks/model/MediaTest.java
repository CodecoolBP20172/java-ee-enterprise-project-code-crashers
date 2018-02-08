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

    @Ignore
    @Test
    @DisplayName("All Fantasy Media")
    public void testGetMediaByGenreValid() {//why valid suffix
        Genre genre = new Genre(Genres.FANTASY);
        assertEquals(4, (Media.getMediaBy(emf, genre)).size());
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