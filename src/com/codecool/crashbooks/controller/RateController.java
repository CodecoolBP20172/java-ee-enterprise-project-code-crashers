package com.codecool.crashbooks.controller;

import com.codecool.crashbooks.model.Media;
import com.codecool.crashbooks.model.Member;
import com.codecool.crashbooks.model.mediaProperty.Rating;
import com.codecool.crashbooks.service.MediaService;
import com.codecool.crashbooks.service.MemberService;
import com.codecool.crashbooks.service.RatingService;
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
public class RateController {

    @Autowired
    MemberService memberService;

    @Autowired
    RatingService ratingService;

    @Autowired
    MediaService mediaService;

    @RequestMapping(value = "/rate/{mediumId}/{ratingValue}", method = RequestMethod.GET)
    public String rateMedium(Model model, HttpSession session, @PathVariable int mediumId, @PathVariable int ratingValue) {
        if (session.getAttribute("id") != null && (ratingValue > 0 && ratingValue < 6)) {
            Member member = memberService.getMemberById((Integer) session.getAttribute("id"));
            Media media = mediaService.getMediasBy(mediumId);

            Rating rating = ratingService.getRatingByMemberAndMedia(member.getId(), media.getId());
            if (rating != null) {
                rating.setStars(ratingValue);
                ratingService.saveRating(rating);
            } else {
                rating = new Rating(ratingValue, member, media);
                ratingService.saveRating(rating);
            }
            return "redirect:/medium/" + mediumId;
        } else {
            model.addAttribute("error", "Forbidden! You don't have permission for this page.");
            return "media/error";
        }
    }
}
