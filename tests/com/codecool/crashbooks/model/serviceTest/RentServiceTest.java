package com.codecool.crashbooks.model.serviceTest;

import com.codecool.crashbooks.model.CopyStatuses;
import com.codecool.crashbooks.service.CopyService;
import com.codecool.crashbooks.service.RentService;
import com.codecool.crashbooks.utility.InitializerBean;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RentServiceTest {

    @Autowired
    private CopyService copyService;
    @Autowired
    private RentService rentService;

    @Test
    @DisplayName("Get rent by ID , VALID")
    public void testGetRentByIdValid() {
        assertEquals("RENTED", rentService.getRentById(1).getCopy().getStatus().name());
    }

    @Test
    @DisplayName("Get rent by ID , INVALID")
    public void testGetRentByIdInvalid() {
        assertEquals(null, rentService.getRentById(42));
    }

    @Test
    @DisplayName("Get all rent of Member by ID 1, VALID")
    public void testGetRentsByMemberIdValid() {
        assertEquals(2, rentService.getRentsByMemberId(1).size());
    }

    @Test
    @DisplayName("Get rent of Copy by ID 1, VALID")
    public void testGetRentsByCopyIdValid() {
        assertEquals(1, rentService.getRentsByCopyId(1).size());
    }

    @Test
    @DisplayName("Get rent of Copy by ID, INVALID")
    public void testGetRentsByCopyIdInvalid() {
        assertEquals(0, rentService.getRentsByCopyId(42).size());
    }

    @Test
    @DisplayName("Get all rent of Member by ID 2, VALID")
    public void testGetRentsByMemberIdValid2() {
        assertEquals(2, rentService.getRentsByMemberId(2).size());
    }

    @Test
    @DisplayName("Get all rent of Member by ID, INVALID")
    public void testGetRentsByMemberIdInvalid() {
        assertEquals(0, rentService.getRentsByMemberId(42).size());
    }

    @Test
    @DisplayName("Get available Media of Member by ID and Status, VALID")
    public void testGetRentByMemberIdAndStatusAvailableValid() {
        assertEquals(1, rentService.getRentsByStatus(CopyStatuses.AVAILABLE).size());
    }

    @Test
    @DisplayName("Get rented Media of Member by ID and Status, VALID")
    public void testGetRentByMemberIdAndStatusRentedValid() {
        assertEquals(1, rentService.getRentsByStatus(CopyStatuses.RENTED).size());
    }

    @Test
    @DisplayName("Get rented Media of Member by ID and Status, VALID")
    public void testGetRentByMemberIdAndStatusPendingValid() {
        assertEquals(2, rentService.getRentsByStatus(CopyStatuses.PENDING).size());
    }

    @Test
    @DisplayName("Get pending Media of Member by ID, VALID")
    public void testGetPendingRentsByMemberIdValid() {
        assertEquals(2, rentService.getPendingRentsByMemberId(2).size());
    }

    @Test
    @DisplayName("Get pending Media of Member by ID, INVALID")
    public void testGetPendingRentsByMemberIdInValid() {
        assertEquals(0, rentService.getPendingRentsByMemberId(42).size());
    }

    @Test
    @DisplayName("Get rented Media of Member by ID, VALID")
    public void testGetRentedRentsByMemberIdValid() {
        assertEquals(1, rentService.getRentedRentsByMemberId(1).size());
    }

    @Test
    @DisplayName("Get rented Media of Member by ID, INVALID")
    public void testGetRentedRentsByMemberIdInValid() {
        assertEquals(0, rentService.getRentedRentsByMemberId(42).size());
    }

    @Test
    @DisplayName("Get rent history of Member by ID, VALID")
    public void testGetReturnedRentsByMemberIdValid() {
        assertEquals(1, rentService.getReturnedRentsByMemberId(1).size());
    }

    @Test
    @DisplayName("Get rent history of Member by ID, INVALID")
    public void testGetReturnedRentsByMemberIdInValid() {
        assertEquals(0, rentService.getReturnedRentsByMemberId(42).size());
    }

}