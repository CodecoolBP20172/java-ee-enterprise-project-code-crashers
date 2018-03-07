package com.codecool.crashbooks.controller;

import com.codecool.crashbooks.model.memberProperty.Membership;
import com.codecool.crashbooks.service.CopyService;
import com.codecool.crashbooks.service.MemberService;
import com.codecool.crashbooks.service.RentService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping(value = "/rent", method = RequestMethod.POST)
    public String rentCopy(HttpSession session, HttpServletRequest req, Model model) {
        String name =(String) session.getAttribute("name");
        if (name == null) {
            model.addAttribute("user_information", "Please Log in!");
            return "book/login";
        }else if(remainingRents((int)session.getAttribute("id"))<=0){
            model.addAttribute("user_information", "Rent limit reached!");
            model.addAttribute("member", name);
            model.addAttribute("id", session.getAttribute("id"));
            model.addAttribute("user_membership", memberService.getMemberByName(name).getMembership());
            model.addAttribute("membershiplist", Arrays.asList(Membership.FREE, Membership.BRONZE, Membership.SILVER, Membership.GOLD));
            return "profile/membership";
        }else{
            rentService.createRent(memberService.getMemberByName(name), copyService.getFirstAvailableCopy(Integer.parseInt(req.getParameter("media_id"))));
        }
        return "redirect:/";
    }

    private int remainingRents(int id){
        int limit = memberService.getMemberById(id).getMembership().getLimit();
        return limit-rentService.getPendingRentsByMemberId(id).size()
                -rentService.getRentedRentsByMemberId(id).size();
    }
}
