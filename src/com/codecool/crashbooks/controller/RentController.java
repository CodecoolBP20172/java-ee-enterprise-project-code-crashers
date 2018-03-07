package com.codecool.crashbooks.controller;

import com.codecool.crashbooks.service.CopyService;
import com.codecool.crashbooks.service.MemberService;
import com.codecool.crashbooks.service.RentService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class RentController {

    @Autowired
    RentService rentService;
    @Autowired
    MemberService memberService;
    @Autowired
    CopyService copyService;

    @RequestMapping(value = "/rent", method = RequestMethod.POST)  // TODO rename to pendingRent
    public String rentCopy(HttpSession session, HttpServletRequest req) {
        if (session.getAttribute("name") != null) {
            rentService.createRent(memberService.getMemberByName((String) session.getAttribute("name")), copyService.getFirstAvailableCopy(Integer.parseInt(req.getParameter("media_id"))));
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/startrent/{rentId}", method = RequestMethod.GET)
    public String startRent(Model model, HttpSession session, @PathVariable int rentId) {
        if (String.valueOf(session.getAttribute("membership")).equals("ADMIN")) {
            rentService.startRent(rentService.getRentById(rentId));
            return "redirect:/admin";
        } else {
            model.addAttribute("error", "Forbidden! You don't have permission for this page.");
            return "book/error";
        }
    }

    @RequestMapping(value = "/endrent/{rentId}", method = RequestMethod.GET)
    public String endRent(Model model, HttpSession session, @PathVariable int rentId) {
        if (String.valueOf(session.getAttribute("membership")).equals("ADMIN")) {
            rentService.endRent(rentService.getRentById(rentId));
            return "redirect:/admin";
        } else {
            model.addAttribute("error", "Forbidden! You don't have permission for this page.");
            return "book/error";
        }
    }
}
