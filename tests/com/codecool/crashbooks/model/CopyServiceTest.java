package com.codecool.crashbooks.model;

import com.codecool.crashbooks.service.CopyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CopyServiceTest extends SetupAndTearDown {
    private CopyService copyService = new CopyService(emf);

    @Test
    @DisplayName("Get All copy")
    public void testGetAllCopy(){
        assertEquals(5, copyService.getAllCopy().size());
    }

    @Test
    @DisplayName("Get copy by Id, for NULL")
    public void testGetCopyByIdForNull(){
        assertNull(copyService.getCopyById(10));
    }

    @Test
    @DisplayName("Get copy by Status")
    public void testGetCopyByStatus(){
        assertEquals(2, copyService.getCopiesByStatus(CopyStatuses.AVAILABLE).size());
    }

    @Test
    @DisplayName("Get the first available copy")
    public void testGetFirstAvailableCopy(){
        assertEquals("testMedia3", copyService.getFirstAvailableCopy(3).getMedia().getTitle());
    }

}