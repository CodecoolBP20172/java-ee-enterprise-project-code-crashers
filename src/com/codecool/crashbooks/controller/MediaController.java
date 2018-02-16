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


    public static ModelAndView renderAllBooks(Request request, Response response, EntityManagerFactory emf) {
        Map<String, Object> params = new HashMap<>();
        params.put("medialist", Media.getAllMedia(emf));
        params.put("genres", Genre.getAllGenre(emf));
        params.put("categories", Category.getAllCategory(emf));
        params.put("member", request.session().attribute("name"));
        return new ModelAndView(params, "book/index");
    }

    public static ModelAndView renderBooksByFilter(Request request, Response response, EntityManagerFactory emf) {
        Map<String, Object> params = new HashMap<>();
        int genreId = Integer.parseInt(request.queryParams("genre"));
        int categoryId = Integer.parseInt(request.queryParams("category"));
        params.put("member", request.session().attribute("name"));
        if (genreId == 0 && categoryId == 0) {
            response.redirect("/");
        } else if (genreId == 0) {
            Category category = Category.getCategoryById(emf, categoryId);
            params.put("category", category);
            params.put("medialist", Media.getMediaBy(emf, category));
        } else if (categoryId == 0) {
            Genre genre = Genre.getGenreById(emf, genreId);
            params.put("genre", genre);
            params.put("category", new Category());
            params.put("medialist", Media.getMediaBy(emf, genre));
        } else {
            Category category = Category.getCategoryById(emf, categoryId);
            Genre genre = Genre.getGenreById(emf, genreId);
            params.put("category", category);
            params.put("genre", genre);
            params.put("medialist", Media.getMediaBy(emf, genre, category));
        }
        params.put("genres", Genre.getAllGenre(emf));
        params.put("categories", Category.getAllCategory(emf));
        return new ModelAndView(params, "book/index");
    }

    public static ModelAndView soon(Request req, Response res) {
        Map<String, Object> params = new HashMap<>();
        params.put("member", req.session().attribute("name"));
        return new ModelAndView(params, "book/soon");
    }
}
