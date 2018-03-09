package com.codecool.crashbooks.controller;

import com.codecool.crashbooks.model.Member;
import com.codecool.crashbooks.model.memberProperty.Membership;
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
import java.util.Arrays;

@Controller
public class RentController {

    @Autowired
    RentService rentService;
    @Autowired
    MemberService memberService;
    @Autowired
    CopyService copyService;

    @RequestMapping(value = "/rent", method = RequestMethod.POST) // TODO rename to pendingRent
    public String rentCopy(HttpSession session, HttpServletRequest req, Model model) {

        if (session.getAttribute("id") != null) {
            int memberId = (int) session.getAttribute("id");
            Member member = memberService.getMemberById(memberId);

            if (member.getMembership().equals(Membership.ADMIN)) {
                return "redirect:/admin";
            }
            if (remainingRents(member.getId()) <= 0) {
                model.addAttribute("user_information", "Rent limit reached!");
                model.addAttribute("memberName", member.getName());
                model.addAttribute("id", session.getAttribute("id"));
                model.addAttribute("user_membership", member.getMembership());
                model.addAttribute("membershiplist", Arrays.asList(Membership.FREE, Membership.BRONZE, Membership.SILVER, Membership.GOLD));
                return "profile/member/membership";
            } else {
                rentService.createRent(member, copyService.getFirstAvailableCopy(Integer.parseInt(req.getParameter("media_id"))));
            }
            return "redirect:/";
        }
        model.addAttribute("user_information", "Please Log in!");
        return "media/login";
    }

    @RequestMapping(value = "/startrent/{rentId}", method = RequestMethod.GET)
    public String startRent(Model model, HttpSession session, @PathVariable int rentId) {
        if (String.valueOf(session.getAttribute("membership")).equals("ADMIN")) {
            rentService.startRent(rentService.getRentById(rentId));
            return "redirect:/admin";
        } else {
            model.addAttribute("error", "Forbidden! You don't have permission for this page.");
            return "media/error";
        }
    }

    @RequestMapping(value = "/endrent/{rentId}", method = RequestMethod.GET)
    public String endRent(Model model, HttpSession session, @PathVariable int rentId) {
        if (String.valueOf(session.getAttribute("membership")).equals("ADMIN")) {
            rentService.endRent(rentService.getRentById(rentId));
            return "redirect:/admin";
        } else {
            model.addAttribute("error", "Forbidden! You don't have permission for this page.");
            return "media/error";
        }
    }

    private int remainingRents(int id){
        int limit = memberService.getMemberById(id).getMembership().getLimit();
        return limit-rentService.getPendingRentsByMemberId(id).size()
                -rentService.getRentedRentsByMemberId(id).size();
    }
}
