package com.codecool.crashbooks.service;

import com.codecool.crashbooks.model.SetupAndTearDown;
import com.codecool.crashbooks.model.mediaproperty.Copy;
import com.codecool.crashbooks.model.mediaproperty.StatusType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CopyServiceTest extends SetupAndTearDownForServices{
    private CopyService copyService = new CopyService(emf);

    @Test
    @DisplayName("Get All copy")
    public void testGetAllCopy(){
        assertEquals(2, copyService.getAllCopy().size());
    }

    @Test
    @DisplayName("Get copy by Id, for NULL")
    public void testGetCopyByIdForNull(){
        assertNull(copyService.getCopyById(10));
    }

    @Test
    @DisplayName("Get copy by Status")
    public void testGetCopyByStatus(){
        assertEquals(1, copyService.getCopyByStatus(StatusType.AVAILABLE).size());
    }

    @Test
    @DisplayName("Get the first available copy")
    public void testGetFirstAvailableCopy(){
        assertEquals("testMedia1", copyService.getFirstAvailableCopy(1).getMedia().getTitle());
    }

}