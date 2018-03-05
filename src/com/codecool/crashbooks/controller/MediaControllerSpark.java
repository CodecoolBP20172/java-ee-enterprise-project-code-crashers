package com.codecool.crashbooks.controller;

import com.codecool.crashbooks.model.mediaProperty.Category;
import com.codecool.crashbooks.model.mediaProperty.Genre;
import com.codecool.crashbooks.service.CategoryService;
import com.codecool.crashbooks.service.GenreService;
import com.codecool.crashbooks.service.MediaService;
import com.codecool.crashbooks.service.RentService;

import java.util.HashMap;
import java.util.Map;

public class MediaControllerSpark {/*

    private final MediaService mediaService;
    private final GenreService genreService;
    private final CategoryService categoryService;
    private final RentService rentService;

    public MediaControllerSpark(MediaService mediaService, GenreService genreService, CategoryService categoryService,
                                RentService rentService) {
        this.mediaService = mediaService;
        this.genreService = genreService;
        this.categoryService = categoryService;
        this.rentService = rentService;
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
            params.put("medialist", mediaService.getMediasBy(category));
        } else if (categoryId == 0) {
            params.put("medialist", mediaService.getMediasBy(genre));
        } else {
            params.put("medialist", mediaService.getMediasBy(genre, category));
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
        params.put("pendingList", rentService.getPendingRentsByMemberId(request.session().attribute("id")));
        params.put("rentedList", rentService.getRentedRentsByMemberId(request.session().attribute("id")));
        params.put("returnedList", rentService.getReturnedRentsByMemberId(request.session().attribute("id")));
        return new ModelAndView(params, "profile/main_profile");
    }

    public ModelAndView soon(Request request, Response response) {
        Map<String, Object> params = new HashMap<>();
        params.put("member", request.session().attribute("name"));
        return new ModelAndView(params, "book/index");
    }
*/
}