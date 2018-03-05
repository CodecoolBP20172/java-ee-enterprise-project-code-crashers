package com.codecool.crashbooks.controller;

import com.codecool.crashbooks.service.CopyService;
import com.codecool.crashbooks.service.MemberService;
import com.codecool.crashbooks.service.RentService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpSession;


public class RentController {

    @Autowired
    RentService rentService;
    @Autowired
    MemberService memberService;
    @Autowired
    CopyService copyService;

    @RequestMapping(value = "/rent", method = RequestMethod.POST)
    public String rentCopy(HttpSession session, HttpServletRequest req) {
        if (session.getAttribute("name") != null) {
            rentService.createRent(memberService.getMemberByName((String) session.getAttribute("name")), copyService.getFirstAvailableCopy(Integer.parseInt(req.getParameter("media_id"))));
        }
        return "book/index";
    }
}
