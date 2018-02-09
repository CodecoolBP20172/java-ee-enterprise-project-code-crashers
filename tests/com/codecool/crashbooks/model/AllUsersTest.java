package com.codecool.crashbooks.model;

import org.apache.commons.lang3.ObjectUtils;
import org.eclipse.persistence.internal.oxm.schema.model.All;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.NoResultException;

import static org.junit.jupiter.api.Assertions.*;

class AllUsersTest extends SetupAndTearDown {

    @Test
    @DisplayName("Get User by ID, VALID")
    public void testGetUserByIdValid(){
        AllUsers user = new AllUsers("test1", "test1");
        assertEquals(user.getName(),AllUsers.getUserById(emf,1).getName());
    }

    @Test
    @DisplayName("Get User by ID, INVALID")
    public void testGetUserByIdInvalid(){
        assertThrows(NoResultException.class, () -> {
            AllUsers.getUserById(emf, 3);
        });
    }

    @Test
    @DisplayName("Get User by name, VALID")
    public void testGetUserByNameValid(){
        AllUsers user = new AllUsers("test1", "test1");
        assertEquals(user.getName(), AllUsers.getUserByName(emf, "test1").getName());
    }

    @Test
    @DisplayName("Get User by name, INVALID")
    public void testGetUserByNameInvalid(){
        assertThrows(NoResultException.class, () -> {
            AllUsers.getUserByName(emf, "anything");
        });
    }

    @Test
    @DisplayName("Test SaveUser")
    public void testSaveUser(){
        AllUsers user = new AllUsers("newUser","newPassword");
        AllUsers.saveUser(emf, user.getName(), user.getPassword());
        assertEquals(user.getName(), AllUsers.getUserByName(emf,"newUser").getName());
    }

}