package com.codecool.crashbooks.model.serviceTest;

import com.codecool.crashbooks.service.MemberService;
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
public class MemberServiceTest {
    @BeforeAll
    public void setup() {
        InitializerBean.setTestRunning(true);
    }
    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("Get Member by ID, VALID")
    public void testGetMemberByIdValid() {
        assertEquals("test", memberService.getMemberById(1).getName());
    }

    @Test
    @DisplayName("Get Member by ID, INVALID")
    public void testGetMemberByIdInvalid() {
        assertEquals(null, memberService.getMemberById(100));
    }

    @Test
    @DisplayName("Get Member by name, VALID")
    public void testGetMemberByNameValid() {
        assertEquals("test1", memberService.getMemberByName("test1").getName());
    }

    @Test
    @DisplayName("Get Member by name, INVALID")
    public void testGetMemberByNameInvalid() {
        assertEquals(null, memberService.getMemberByName("anything"));
    }

    @Test
    @DisplayName("Test SaveMember")
    public void testSaveMember() {
        memberService.saveMember("newMember", "newPassword");
        assertEquals("newMember", memberService.getMemberByName("newMember").getName());
    }

}