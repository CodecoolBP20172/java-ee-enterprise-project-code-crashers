package com.codecool.crashbooks.controller;

import com.codecool.crashbooks.model.Media;
import com.codecool.crashbooks.model.mediaproperty.Category;
import com.codecool.crashbooks.model.mediaproperty.Genre;
import com.codecool.crashbooks.service.CategoryService;
import com.codecool.crashbooks.service.GenreService;
import com.codecool.crashbooks.service.MediaService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

public class MediaController {

    private MediaService mediaService;
    private GenreService genreService;
    private CategoryService categoryService;

    public MediaController(MediaService mediaService, GenreService genreService, CategoryService categoryService) {
        this.mediaService = mediaService;
        this.genreService = genreService;
        this.categoryService = categoryService;
    }

    public ModelAndView renderAllBooks(Request request, Response response, EntityManagerFactory emf) {
        Map<String, Object> params = new HashMap<>();
        params.put("medialist", mediaService.getAllMedia(emf));
        params.put("genres", genreService.getAllGenre());
        params.put("categories", categoryService.getAllCategory());
        params.put("member", request.session().attribute("name"));
        return new ModelAndView(params, "book/index");
    }

    public ModelAndView renderBooksByFilter(Request request, Response response, EntityManagerFactory emf) {
        Map<String, Object> params = new HashMap<>();
        int genreId = Integer.parseInt(request.queryParams("genre"));
        int categoryId = Integer.parseInt(request.queryParams("category"));

        Genre genre = genreId == 0 ? null : genreService.getGenreById(genreId);
        Category category = categoryId == 0 ? null : categoryService.getCategoryById(categoryId);

        if (genreId == 0 && categoryId == 0) {
            response.redirect("/");
        } else if (genreId == 0) {
            params.put("medialist", mediaService.getMediaBy(emf, category));
        } else if (categoryId == 0) {
            params.put("medialist", mediaService.getMediaBy(emf, genre));
        } else {
            params.put("medialist", mediaService.getMediaBy(emf, genre, category));
        }
        params.put("genre", genre);
        params.put("genres", genreService.getAllGenre());
        params.put("category", category);
        params.put("categories", categoryService.getAllCategory());
        params.put("member", request.session().attribute("name"));
        return new ModelAndView(params, "book/index");
    }

    public ModelAndView soon(Request req, Response res) {
        Map<String, Object> params = new HashMap<>();
        params.put("member", req.session().attribute("name"));
        return new ModelAndView(params, "profile/main_profile");
    }
}
