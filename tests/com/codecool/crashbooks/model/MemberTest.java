package com.codecool.crashbooks.model;

import com.codecool.crashbooks.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest extends SetupAndTearDown {
    MemberService memberService = new MemberService();

    @Test
    @DisplayName("Get Member by ID, VALID")
    public void testGetMemberByIdValid(){
        assertEquals("test1", memberService.getMemberById(emf,1).getName());
    }

    @Test
    @DisplayName("Get Member by ID, INVALID")
    public void testGetMemberByIdInvalid(){
        assertEquals(null, memberService.getMemberById(emf, 3));
    }

    @Test
    @DisplayName("Get Member by name, VALID")
    public void testGetMemberByNameValid(){
        assertEquals("test1", memberService.getMemberByName(emf, "test1").getName());
    }

    @Test
    @DisplayName("Get Member by name, INVALID")
    public void testGetMemberByNameInvalid(){
        assertEquals(null, memberService.getMemberByName(emf, "anything"));
    }

    @Test
    @DisplayName("Test SaveMember")
    public void testSaveMember(){
        memberService.saveMember(emf, "newMember", "newPassword");
        assertEquals("newMember", memberService.getMemberByName(emf,"newMember").getName());
    }

}