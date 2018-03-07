package com.codecool.crashbooks.controller;

import com.codecool.crashbooks.model.CopyStatuses;
import com.codecool.crashbooks.model.Member;
import com.codecool.crashbooks.service.MemberService;
import com.codecool.crashbooks.service.RentService;
import com.codecool.crashbooks.utility.Password;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@Scope("session")
public class MemberController {

    @Autowired
    MemberService memberService;

    @Autowired
    RentService rentService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String renderLoginPage(){
        return "book/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest req, HttpSession session, Model model){
        Member member = memberService.getMemberByName(req.getParameter("name"));
        if(member != null && Password.checkPassword(req.getParameter("password"), member.getPassword())){
            session.setAttribute("name", req.getParameter("name"));
            session.setAttribute("id", member.getId());
            session.setAttribute("membership", member.getMembership());
            if(String.valueOf(session.getAttribute("membership")).equals("ADMIN")) {
                return "redirect:/admin";
            } else {
                return "redirect:/";
            }
        }else{
            model.addAttribute("error", "Login Failed! Username or Password invalid!");
            return "book/error";
        }
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String renderRegistrationPage(){
        return "book/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String register(Model model, HttpServletRequest req, HttpSession session){
        if(checkIsMemberNameFree(req.getParameter("name"))){
            saveMember(req);
            Member member = memberService.getMemberByName(req.getParameter("name"));
            session.setAttribute("name", req.getParameter("name"));
            session.setAttribute("id", member.getId());
            return "redirect:/";
        }else{
            model.addAttribute("error", "Registration Failed!");
            return "book/error";
        }
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String renderProfile(Model model, HttpSession session){
        model.addAttribute("memberName", session.getAttribute("name"));
        model.addAttribute("pendingList", rentService.getPendingRentsByMemberId((int)session.getAttribute("id")));
        model.addAttribute("rentedList", rentService.getRentedRentsByMemberId((int)session.getAttribute("id")));
        model.addAttribute("returnedList", rentService.getReturnedRentsByMemberId((int)session.getAttribute("id")));
        return "profile/main_profile";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String renderAdmin(Model model, HttpSession session){
        if(String.valueOf(session.getAttribute("membership")).equals("ADMIN")) {
            model.addAttribute("memberName", session.getAttribute("name"));
            model.addAttribute("pendingList", rentService.getRentsByStatus(CopyStatuses.PENDING));
            model.addAttribute("rentedList", rentService.getRentsByStatus(CopyStatuses.RENTED));
            model.addAttribute("returnedList", rentService.getRentsByStatus(CopyStatuses.AVAILABLE));
            return "profile/admin_profile";
        } else {
            model.addAttribute("error", "Forbidden! You don't have permission for this page.");
            return "book/error";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    private void saveMember(HttpServletRequest req) {
        memberService.saveMember(req.getParameter("name"), Password.hashPassword(req.getParameter("password")));
    }

    private boolean checkIsMemberNameFree(String name) {
        return (memberService.getMemberByName(name)== null);
    }
}
