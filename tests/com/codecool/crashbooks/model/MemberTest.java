package com.codecool.crashbooks.model;

import com.codecool.crashbooks.controller.MemberController;
import com.codecool.crashbooks.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.NoResultException;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest extends SetupAndTearDown {
    MemberService memberService = new MemberService();

    @Test
    @DisplayName("Get Member by ID, VALID")
    public void testGetMemberByIdValid(){
        Member member = new Member("test1", "test1");
        assertEquals(member.getName(), memberService.getMemberById(emf,1).getName());
    }

    @Test
    @DisplayName("Get Member by ID, INVALID")
    public void testGetMemberByIdInvalid(){
        assertThrows(NoResultException.class, () -> {
            memberService.getMemberById(emf, 3);
        });
    }

    @Test
    @DisplayName("Get Member by name, VALID")
    public void testGetMemberByNameValid(){
        Member member = new Member("test1", "test1");
        assertEquals(member.getName(), memberService.getMemberByName(emf, "test1").getName());
    }

    @Test
    @DisplayName("Get Member by name, INVALID")
    public void testGetMemberByNameInvalid(){
        assertEquals(null, memberService.getMemberByName(emf, "anything"));
    }

    @Test
    @DisplayName("Test SaveMember")
    public void testSaveMember(){
        Member member = new Member("newMember","newPassword");
        memberService.saveMember(emf, member.getName(), member.getPassword());
        assertEquals(member.getName(), memberService.getMemberByName(emf,"newMember").getName());
    }

}