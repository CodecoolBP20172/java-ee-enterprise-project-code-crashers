package com.codecool.crashbooks.model;

import com.codecool.crashbooks.service.CopyService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CopyServiceTest {

    @Autowired
    private CopyService copyService;

    @Test
    @DisplayName("Get All copy")
    public void testGetAllCopy() {
        assertEquals(5, copyService.getAllCopy().size());
    }

    @Test
    @DisplayName("Get copy by Id, for NULL")
    public void testGetCopyByIdForNull() {
        assertNull(copyService.getCopyById(10));
    }

    @Test
    @DisplayName("Get copy by Status")
    public void testGetCopyByStatus() {
        assertEquals(2, copyService.getCopiesByStatus(CopyStatuses.AVAILABLE).size());
    }

    @Test
    @DisplayName("Get the first available copy")
    public void testGetFirstAvailableCopy() {
        assertEquals("testMedia3", copyService.getFirstAvailableCopy(3).getMedia().getTitle());
    }

}