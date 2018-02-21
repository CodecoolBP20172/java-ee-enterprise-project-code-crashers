package com.codecool.crashbooks.model;

import com.codecool.crashbooks.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest extends SetupAndTearDown {
    MemberService memberService = new MemberService(emf);

    @Test
    @DisplayName("Get Member by ID, VALID")
    public void testGetMemberByIdValid(){
        assertEquals("test1", memberService.getMemberById(1).getName());
    }

    @Test
    @DisplayName("Get Member by ID, INVALID")
    public void testGetMemberByIdInvalid(){
        assertEquals(null, memberService.getMemberById(3));
    }

    @Test
    @DisplayName("Get Member by name, VALID")
    public void testGetMemberByNameValid(){
        assertEquals("test1", memberService.getMemberByName("test1").getName());
    }

    @Test
    @DisplayName("Get Member by name, INVALID")
    public void testGetMemberByNameInvalid(){
        assertEquals(null, memberService.getMemberByName("anything"));
    }

    @Test
    @DisplayName("Test SaveMember")
    public void testSaveMember(){
        memberService.saveMember("newMember", "newPassword");
        assertEquals("newMember", memberService.getMemberByName("newMember").getName());
    }

}