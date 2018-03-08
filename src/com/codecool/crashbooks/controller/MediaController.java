package com.codecool.crashbooks.controller;

import com.codecool.crashbooks.model.Member;
import com.codecool.crashbooks.model.mediaProperty.Category;
import com.codecool.crashbooks.model.mediaProperty.Genre;
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
public class MediaController {

    @Autowired
    MediaService mediaService;
    @Autowired
    GenreService genreService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    MemberService memberService;
    @Autowired
    RatingService ratingService;
    @Autowired
    ReviewService reviewService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String renderAllMedia(Model model, HttpSession session){
        model.addAttribute("medialist", mediaService.getAllMedia());
        model.addAttribute("genres", genreService.getAllGenre());
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("memberName", session.getAttribute("name"));
        return "media/index";
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public String renderMediaByFilter(Model model, HttpServletRequest req, HttpSession session){
        int genreId = Integer.parseInt(req.getParameter("genre"));
        int categoryId = Integer.parseInt(req.getParameter("category"));

        Genre genre = genreId == 0 ? null:genreService.getGenreById(genreId);
        Category category = categoryId == 0 ? null:categoryService.getCategoryById(categoryId);

        if(genreId ==0 && categoryId ==0){
            return "index";
        }else if (genreId ==0){
            model.addAttribute("medialist", mediaService.getMediasBy(category));
        } else if (categoryId ==0){
            model.addAttribute("medialist", mediaService.getMediasBy(genre));
        } else{
            model.addAttribute("medialist", mediaService.getMediasBy(genre, category));
        }

        model.addAttribute("genre", genre);
        model.addAttribute("genres", genreService.getAllGenre());
        model.addAttribute("category", category);
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("member", session.getAttribute("name"));
        return "media/index";
    }

    @RequestMapping(value = "/medium/{id}", method = RequestMethod.GET)
    public String renderBookReviewPage(@PathVariable String id, HttpSession session, Model model){
        Member member = memberService.getMemberById((int) session.getAttribute("name"));
        if (member != null){
            model.addAttribute("userReview", reviewService.getReviewByMemberAndMedia((int)session.getAttribute("id"), Integer.parseInt(id)));
            model.addAttribute("userRating", ratingService.getRatingByMemberAndMedia((int)session.getAttribute("id"), Integer.parseInt(id)));
            model.addAttribute("memberName", member.getName());
        }
        model.addAttribute("medium", mediaService.getMediasBy(Integer.parseInt(id)));
        return "media/book_review";
    }

    @RequestMapping(value = "/soon", method = RequestMethod.POST)
    public String renderSoon(Model model, HttpSession session){
        model.addAttribute("member",session.getAttribute("name"));
        return "media/index";
    }

}
