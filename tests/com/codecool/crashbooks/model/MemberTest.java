package com.codecool.crashbooks.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.NoResultException;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest extends SetupAndTearDown {

    @Test
    @DisplayName("Get Member by ID, VALID")
    public void testGetMemberByIdValid(){
        Member member = new Member("test1", "test1");
        assertEquals(member.getName(), Member.getMemberById(emf,1).getName());
    }

    @Test
    @DisplayName("Get Member by ID, INVALID")
    public void testGetMemberByIdInvalid(){
        assertThrows(NoResultException.class, () -> {
            Member.getMemberById(emf, 3);
        });
    }

    @Test
    @DisplayName("Get Member by name, VALID")
    public void testGetMemberByNameValid(){
        Member member = new Member("test1", "test1");
        assertEquals(member.getName(), Member.getMemberByName(emf, "test1").getName());
    }

    @Test
    @DisplayName("Get Member by name, INVALID")
    public void testGetMemberByNameInvalid(){
        assertThrows(NoResultException.class, () -> {
            Member.getMemberByName(emf, "anything");
        });
    }

    @Test
    @DisplayName("Test SaveMember")
    public void testSaveMember(){
        Member member = new Member("newMember","newPassword");
        Member.saveMember(emf, member.getName(), member.getPassword());
        assertEquals(member.getName(), Member.getMemberByName(emf,"newMember").getName());
    }

}