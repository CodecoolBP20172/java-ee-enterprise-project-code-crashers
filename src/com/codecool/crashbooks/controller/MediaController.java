package com.codecool.crashbooks.controller;

import com.codecool.crashbooks.model.mediaproperty.Category;
import com.codecool.crashbooks.model.mediaproperty.Genre;
import com.codecool.crashbooks.service.CategoryService;
import com.codecool.crashbooks.service.GenreService;
import com.codecool.crashbooks.service.MediaService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

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

    public ModelAndView renderAllMedia(Request request, Response response) {
        Map<String, Object> params = new HashMap<>();
        params.put("medialist", mediaService.getAllMedia());
        params.put("genres", genreService.getAllGenre());
        params.put("categories", categoryService.getAllCategory());
        params.put("member", request.session().attribute("name"));
        return new ModelAndView(params, "book/index");
    }

    public ModelAndView renderMediaByFilter(Request request, Response response) {
        Map<String, Object> params = new HashMap<>();
        int genreId = Integer.parseInt(request.queryParams("genre"));
        int categoryId = Integer.parseInt(request.queryParams("category"));

        Genre genre = genreId == 0 ? null : genreService.getGenreById(genreId);
        Category category = categoryId == 0 ? null : categoryService.getCategoryById(categoryId);

        if (genreId == 0 && categoryId == 0) {
            response.redirect("/");
        } else if (genreId == 0) {
            params.put("medialist", mediaService.getMediaBy(category));
        } else if (categoryId == 0) {
            params.put("medialist", mediaService.getMediaBy(genre));
        } else {
            params.put("medialist", mediaService.getMediaBy(genre, category));
        }
        params.put("genre", genre);
        params.put("genres", genreService.getAllGenre());
        params.put("category", category);
        params.put("categories", categoryService.getAllCategory());
        params.put("member", request.session().attribute("name"));
        return new ModelAndView(params, "book/index");
    }

    public ModelAndView renderProfile(Request request, Response response) {
        Map<String, Object> params = new HashMap<>();
        params.put("member", request.session().attribute("name"));
        return new ModelAndView(params, "profile/main_profile");
    }

    public ModelAndView soon(Request request, Response response) {
        Map<String, Object> params = new HashMap<>();
        params.put("member", request.session().attribute("name"));
        return new ModelAndView(params, "book/index");
    }

}
