package com.codecool.crashbooks.controller;

import com.codecool.crashbooks.model.Media;
import com.codecool.crashbooks.model.bookproperty.Author;
import com.codecool.crashbooks.model.bookproperty.Category;
import com.codecool.crashbooks.model.bookproperty.Genre;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codecool.crashbooks.model.Media;

public class BookController {


    public static ModelAndView renderAllBooks(Request request, Response response) {
        List<Media> bookList = new ArrayList<>();
        Map<String, List> params = new HashMap<>();

        params.put("booklist", bookList);
        return new ModelAndView(params, "book/index");
    }
}
