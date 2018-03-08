package com.codecool.crashbooks.controller;

import com.codecool.crashbooks.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

    @Controller
    @Scope("session")
    public class ReviewController {

        @Autowired
        RatingService reviewService;

        @RequestMapping(value="/review/{id}", method = RequestMethod.GET)
        public String renderRatePage(HttpSession session, Model model, @PathVariable String id){
            String name =(String) session.getAttribute("name");
            if (name != null) {
                model.addAttribute("user_information", "Please Log in!");
                return "redirect:/login";
            }
            model.addAttribute("memberName", name);
            model.addAttribute("mediumId", id);
            return "media/review";
        }

        @RequestMapping(value="/review/{id}", method = RequestMethod.POST)
        public String rate(HttpSession session, Model model, @PathVariable String id, HttpRequest req){

            return "redirect:/medium/"+id;
        }
}
