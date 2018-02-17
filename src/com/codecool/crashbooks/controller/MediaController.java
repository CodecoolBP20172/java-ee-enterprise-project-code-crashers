package com.codecool.crashbooks.controller;

import com.codecool.crashbooks.model.Media;
import com.codecool.crashbooks.model.mediaproperty.Category;
import com.codecool.crashbooks.model.mediaproperty.Genre;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

public class MediaController {

    private MediaController() {
    }

    private static class LazyHolder {
        static final MediaController INSTANCE = new MediaController();
    }

    public static MediaController getInstance() {
        return MediaController.LazyHolder.INSTANCE;
    }

    public ModelAndView renderAllBooks(Request request, Response response, EntityManagerFactory emf) {
        Map<String, Object> params = new HashMap<>();
        params.put("medialist", Media.getAllMedia(emf));
        params.put("genres", Genre.getAllGenre(emf));
        params.put("categories", Category.getAllCategory(emf));
        params.put("member", request.session().attribute("name"));
        return new ModelAndView(params, "book/index");
    }

    public ModelAndView renderBooksByFilter(Request request, Response response, EntityManagerFactory emf) {
        Map<String, Object> params = new HashMap<>();
        int genreId = Integer.parseInt(request.queryParams("genre"));
        int categoryId = Integer.parseInt(request.queryParams("category"));

        Genre genre = genreId == 0 ? null : Genre.getGenreById(emf, genreId);
        Category category = categoryId == 0 ? null : Category.getCategoryById(emf, categoryId);

        if (genreId == 0 && categoryId == 0) {
            response.redirect("/");
        } else if (genreId == 0) {
            params.put("medialist", Media.getMediaBy(emf, category));
        } else if (categoryId == 0) {
            params.put("medialist", Media.getMediaBy(emf, genre));
        } else {
            params.put("medialist", Media.getMediaBy(emf, genre, category));
        }
        params.put("genre", genre);
        params.put("genres", Genre.getAllGenre(emf));
        params.put("category", category);
        params.put("categories", Category.getAllCategory(emf));
        params.put("member", request.session().attribute("name"));
        return new ModelAndView(params, "book/index");
    }

    public ModelAndView soon(Request req, Response res) {
        Map<String, Object> params = new HashMap<>();
        params.put("member", req.session().attribute("name"));
        return new ModelAndView(params, "book/soon");
    }
}
