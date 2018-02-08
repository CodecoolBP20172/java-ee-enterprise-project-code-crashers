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
    @DisplayName("Size check for allMediaByCategory, BOOK")
    public void testGetAllMediaByCategoryValidSizeForBooks(){
        Category category = Category.getCategoryById(emf, 1);
        assertEquals(2, Media.getMediaBy(emf, category).size());
    }

    @Test
    @DisplayName("Size check for allMediaByCategory, MAGAZINE")
    public void testGetAllMediaByCategorzValidSizeForMagazines(){
        Category category = Category.getCategoryById(emf, 2);
        assertEquals(1, Media.getMediaBy(emf, category).size());
    }

    @Test
    @DisplayName("Check for invalid category")
    public void testGetAllMediaByCategory(){
        Category category = null;
        assertThrows(NullPointerException.class, ()->{
            Media.getMediaBy(emf, category);
        });
    }



    }
