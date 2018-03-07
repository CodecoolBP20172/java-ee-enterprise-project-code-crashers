package com.codecool.crashbooks.controller;

import com.codecool.crashbooks.model.Member;
import com.codecool.crashbooks.model.memberProperty.Membership;
import com.codecool.crashbooks.service.MemberService;
import com.codecool.crashbooks.utility.Password;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
@Scope("session")
public class MemberController {

    @Autowired
    MemberService memberService;

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
            return "redirect:/";
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
        if(memberService.checkIsMemberNameFree(req.getParameter("name"))){
            memberService.saveMember(req.getParameter("name"), req.getParameter("password"));
            Member member = memberService.getMemberByName(req.getParameter("name"));
            session.setAttribute("name", req.getParameter("name"));
            session.setAttribute("id", member.getId());
            return "redirect:/";
        }else{
            model.addAttribute("error", "Registration Failed!");
            return "book/error";
        }

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/edit/{editable}", method = RequestMethod.GET) //TODO find a good name for editable
    public String renderEditPage(HttpSession session, Model model, @PathVariable("editable") String editable){
        if(editable.equals("username") || editable.equals("username")){
            model.addAttribute("member", session.getAttribute("name"));
            model.addAttribute("editable", editable);
            return "profile/edit";
        }
        model.addAttribute("error", "There is no such route!");
        return "book/error";
    }

    @RequestMapping(value = "/edit/{editable}", method = RequestMethod.POST) //TODO find a good name for editable, possibly make it two different route
    public String edit(HttpServletRequest req, HttpSession session, Model model, @PathVariable("editable") String editable) {
        if (editable.equals("username")) {
            if (memberService.checkIsMemberNameFree(req.getParameter("name"))) {

                memberService.editUsername(req.getParameter("name"),
                        (int) session.getAttribute("id"));
                session.setAttribute("name", req.getParameter("name"));
                return "redirect:/profile";
            } else {
                model.addAttribute("error", "Username change have failed!");
            }
        } else if (editable.equals("password")) {
            if (req.getParameter("psw1").equals(req.getParameter("psw2"))) {
                memberService.editPassword(req.getParameter("psw1"),
                        (int) session.getAttribute("id"));
                return "redirect:/profile";
            } else {
                model.addAttribute("error", "Passwords are not the same!");
            }
        }
        return "book/error";
    }

    @RequestMapping(value = "/membership", method = RequestMethod.GET)
    public String membership(Model model, HttpSession session){
        String name = (String) session.getAttribute("name");
        if (name != null){
            model.addAttribute("member", name);
            model.addAttribute("id", session.getAttribute("id"));
            model.addAttribute("membership", memberService.getMemberByName(name).getMembership());
            model.addAttribute("membershiplist", Arrays.asList(Membership.FREE, Membership.BRONZE, Membership.SILVER, Membership.GOLD));
            return "profile/membership";
        }
        return "redirect:/registration";
    }
}
