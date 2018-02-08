package com.codecool.crashbooks.controller;

import com.codecool.crashbooks.model.Media;
import com.codecool.crashbooks.model.bookproperty.Author;
import com.codecool.crashbooks.model.bookproperty.Category;
import com.codecool.crashbooks.model.bookproperty.Genre;
import com.codecool.crashbooks.model.bookproperty.Genres;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookController {


    public static ModelAndView renderAllBooks(Request request, Response response, EntityManagerFactory emf) {
        Map<String, Object> params = new HashMap<>();

        params.put("medialist", Media.getAllMedia(emf));
        return new ModelAndView(params, "book/index");
    }
}
