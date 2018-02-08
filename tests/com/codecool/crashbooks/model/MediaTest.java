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
    public void testGetAllMediaByCategoryValidSizeForMagazines(){
        Category category = Category.getCategoryById(emf, 2);
        assertEquals(1, Media.getMediaBy(emf, category).size());
    }

    @Test
    @DisplayName("Check for invalid category")
    public void testGetAllMediaByCategoryForInvalidCategory(){
        Category category = null;
        assertThrows(NullPointerException.class, ()->{
            Media.getMediaBy(emf, category);
        });
    }

    @Test
    @DisplayName("Check for valid type, MAGAZINE")
    public void testGetAllMediaByCategoryForMagazineType(){
        Category exceptedCategory = Category.getCategoryById(emf, 2);
        Category realCategory = Media.getAllMedia(emf).get(0).getCategory();
        assertEquals(exceptedCategory.getName(), realCategory.getName());
    }

    @Test
    @DisplayName("Check for valid type, Book")
    public void testGetAllMediaByCategoryForBookType(){
        Category exceptedCategory = Category.getCategoryById(emf, 1);
        Category realCategory = Media.getAllMedia(emf).get(1).getCategory();
        assertEquals(exceptedCategory.getName(), realCategory.getName());
    }


    @Test
    @DisplayName("Size check for allMediaByCategory, testAuthor1")
    public void testGetAllMediaByCategoryForTestAuthor1(){
        Author author = Author.getAuthorById(emf, 1);
        assertEquals(2, Media.getMediaBy(emf, author).size());
    }



    }
