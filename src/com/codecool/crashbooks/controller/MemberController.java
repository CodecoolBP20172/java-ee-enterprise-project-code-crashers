package com.codecool.crashbooks.controller;

import com.codecool.crashbooks.model.CopyStatuses;
import com.codecool.crashbooks.model.Member;
import com.codecool.crashbooks.model.memberProperty.Membership;
import com.codecool.crashbooks.service.MemberService;
import com.codecool.crashbooks.service.RentService;
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

    private String rentTab = "pending";

    @Autowired
    MemberService memberService;

    @Autowired
    RentService rentService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String renderLoginPage(){
        return "media/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest req, HttpSession session, Model model) {
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
            return "media/error";
        }
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String renderRegistrationPage(){
        return "media/registration";
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
            return "media/error";
        }
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String renderProfile(Model model, HttpSession session) {
        if (session.getAttribute("id") != null) {
            int memberId = (int) session.getAttribute("id");
            Member member = memberService.getMemberById(memberId);

            if(member.getMembership().equals(Membership.ADMIN)) {
                return "redirect:/admin";
            }
            model.addAttribute("memberName", member.getName());
            model.addAttribute("membership", member.getMembership());
            model.addAttribute("pendingList", rentService.getPendingRentsByMemberId(member.getId()));
            model.addAttribute("rentedList", rentService.getRentedRentsByMemberId(member.getId()));
            model.addAttribute("returnedList", rentService.getReturnedRentsByMemberId(member.getId()));
            return "profile/main_profile";
        }
        model.addAttribute("user_information", "Please log in to see profile information!");
        return "media/login";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String renderAdmin(Model model, HttpSession session) {
        if(String.valueOf(session.getAttribute("membership")).equals("ADMIN")) {
            model.addAttribute("memberName", session.getAttribute("name"));
            model.addAttribute("rentTab", rentTab);
            model.addAttribute("pendingList", rentService.getRentsByStatus(CopyStatuses.PENDING));
            model.addAttribute("rentedList", rentService.getRentsByStatus(CopyStatuses.RENTED));
            model.addAttribute("returnedList", rentService.getRentsByStatus(CopyStatuses.AVAILABLE));
            return "profile/admin_profile";
        } else {
            if (session.getAttribute("id")!= null) {
                model.addAttribute("memberName", session.getAttribute("name"));
            }
            model.addAttribute("error", "Forbidden! You don't have permission for this page.");
            return "media/error";
        }
    }

    @RequestMapping(value = "/admin/pending_rents", method = RequestMethod.GET)
    public String renderPendingAdmin(Model model, HttpSession session){
        if(String.valueOf(session.getAttribute("membership")).equals("ADMIN")) {
            rentTab = "pending";
            return "redirect:/admin";
        } else {
            if (session.getAttribute("id")!= null) {
                model.addAttribute("memberName", session.getAttribute("name"));
            }
            model.addAttribute("error", "Forbidden! You don't have permission for this page.");
            return "media/error";
        }
    }

    @RequestMapping(value = "/admin/rented_rents", method = RequestMethod.GET)
    public String renderRentedAdmin(Model model, HttpSession session){
        if(String.valueOf(session.getAttribute("membership")).equals("ADMIN")) {
            rentTab = "rented";
            return "redirect:/admin";
        } else {
            if (session.getAttribute("id")!= null) {
                model.addAttribute("memberName", session.getAttribute("name"));
            }
            model.addAttribute("error", "Forbidden! You don't have permission for this page.");
            return "media/error";
        }
    }

    @RequestMapping(value = "/admin/returned_rents", method = RequestMethod.GET)
    public String renderReturnedAdmin(Model model, HttpSession session){
        if(String.valueOf(session.getAttribute("membership")).equals("ADMIN")) {
            rentTab = "returned";
            return "redirect:/admin";
        } else {
            if (session.getAttribute("id")!= null) {
                model.addAttribute("memberName", session.getAttribute("name"));
            }
            model.addAttribute("error", "Forbidden! You don't have permission for this page.");
            return "media/error";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/edit/{editable}", method = RequestMethod.GET) //TODO find a good name for editable
    public String renderEditPage(HttpSession session, Model model, @PathVariable("editable") String editable){
        if(editable.equals("username") || editable.equals("password")){
            model.addAttribute("memberName", session.getAttribute("name"));
            model.addAttribute("editable", editable);
            return "profile/member/edit";
        }
        model.addAttribute("error", "There is no such route!");
        return "media/error";
    }

    @RequestMapping(value = "/edit/{editable}", method = RequestMethod.POST) //TODO find a good name for editable, possibly make it two different route
    public String edit(HttpServletRequest req, HttpSession session, Model model, @PathVariable("editable") String editable) {
        if (session.getAttribute("id") != null) {
            int memberId = (int) session.getAttribute("id");

            if (editable.equals("username")) {
                if (memberService.checkIsMemberNameFree(req.getParameter("name"))) {

                    memberService.editUsername(req.getParameter("name"), memberId);
                    session.setAttribute("name", req.getParameter("name"));
                    return "redirect:/profile";
                } else {
                    model.addAttribute("error", "Username change have failed!");
                }
            } else if (editable.equals("password")) {
                if (req.getParameter("psw1").equals(req.getParameter("psw2"))) {
                    memberService.editPassword(req.getParameter("psw1"), memberId);
                    return "redirect:/profile";
                } else {
                    model.addAttribute("error", "Passwords are not the same!");
                }
            }
            return "media/error";
        }
        model.addAttribute("user_information", "You are not allowed to make this request");
        return "media/error";

    }

    @RequestMapping(value = "/membership", method = RequestMethod.GET)
    public String renderMembershipPage(Model model, HttpSession session) {
        if (session.getAttribute("id") != null) {
            int memberId = (int) session.getAttribute("id");
            Member member = memberService.getMemberById(memberId);

            if(member.getMembership().equals(Membership.ADMIN)) {
                return "redirect:/admin";
            }
            model.addAttribute("memberName", member.getName());
            model.addAttribute("id", session.getAttribute("id"));
            model.addAttribute("user_membership", member.getMembership());
            model.addAttribute("membershiplist", Arrays.asList(Membership.FREE, Membership.BRONZE, Membership.SILVER, Membership.GOLD));
            return "profile/member/membership";
        }
        model.addAttribute("user_information", "Please log in to see membership information!");
        return "media/login";
    }

    @RequestMapping(value = "/membership", method = RequestMethod.POST)
    public String membership(HttpServletRequest req, HttpSession session, Model model) {
        if (session.getAttribute("id") != null) {
            int memberId = (int) session.getAttribute("id");
            Member member = memberService.getMemberById(memberId);

            if (member.getMembership().equals(Membership.ADMIN)) {
                return "redirect:/admin";
            }
            model.addAttribute("memberName", session.getAttribute("name"));
            model.addAttribute("pendingList", rentService.getPendingRentsByMemberId(memberId));
            model.addAttribute("rentedList", rentService.getRentedRentsByMemberId(memberId));
            model.addAttribute("returnedList", rentService.getReturnedRentsByMemberId(memberId));
            memberService.setMembershipById(memberId, req.getParameter("membership_type"));
            return "redirect:/profile";
        }
        model.addAttribute("user_information", "You are not allowed to make this request");
        return "media/error";
    }
}
