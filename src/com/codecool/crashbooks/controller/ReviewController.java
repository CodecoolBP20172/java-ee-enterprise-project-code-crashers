package com.codecool.crashbooks.controller;

import com.codecool.crashbooks.model.Media;
import com.codecool.crashbooks.model.Member;
import com.codecool.crashbooks.model.mediaProperty.Review;
import com.codecool.crashbooks.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

    @Controller
    @Scope("session")
    public class ReviewController {

        @Autowired
        ReviewService reviewService;
        @Autowired
        MemberService memberService;
        @Autowired
        MediaService mediaService;
        @Autowired
        RatingService ratingService;
        @Autowired
        RentService rentService;

        @RequestMapping(value="/review/{id}", method = RequestMethod.GET)
        public String renderReviewPage(HttpSession session, Model model, @PathVariable String id){
            String name =(String) session.getAttribute("name");
            if (name == null) {
                model.addAttribute("user_information", "Please Log in!");
                return "media/login";
            }
            if (String.valueOf(session.getAttribute("membership")).equals("ADMIN")) {
                return "redirect:/admin";
            }
            model.addAttribute("memberName", name);
            model.addAttribute("mediumId", id);
            return "media/review";
        }

        @RequestMapping(value="/review/{id}", method = RequestMethod.POST)
        public String review(HttpSession session, Model model, @PathVariable String id, HttpServletRequest req){
            String name =(String) session.getAttribute("name");
            if (name == null) {
                model.addAttribute("user_information", "Please Log in!");
                return "media/login";
            }

            if (String.valueOf(session.getAttribute("membership")).equals("ADMIN")) {
                return "redirect:/admin";
            }

            Member member = memberService.getMemberByName(name);
            Media media = mediaService.getMediasBy(Integer.parseInt(id));

                Review review = new Review(req.getParameter("rating"), member, media);
                reviewService.saveReview(review);
            if (session.getAttribute("id")!= null){
                model.addAttribute("userReview", reviewService.getReviewByMemberAndMedia((int)session.getAttribute("id"), Integer.parseInt(id)));
                model.addAttribute("userRating", ratingService.getRatingByMemberAndMedia((int)session.getAttribute("id"), Integer.parseInt(id)));
                model.addAttribute("memberName", member.getName());
            }
            model.addAttribute("medium", mediaService.getMediasBy(Integer.parseInt(id)));
            model.addAttribute("nextAvailableRentDate", rentService.getNextAvailableRentDate(Integer.parseInt(id)));
            model.addAttribute("averageRating", mediaService.getMediasBy(Integer.parseInt(id)).getAverageRating());
            return "redirect:/medium/"+id;
        }
}
