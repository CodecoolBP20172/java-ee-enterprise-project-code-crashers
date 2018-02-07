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

public class BookController {


    public static ModelAndView renderAllBooks(Request request, Response response) {
        //testMedia can be deleted
        List<Media> mediaList = new ArrayList<>();//Media.getAll();
        Media media1 = new Media("testtitle1", "testdesc1");
        Media media2 = new Media("testtitle2", "testdesc2");
        mediaList.add(media1);
        mediaList.add(media2);
        //mediaList will initialized by a Media getAll() method
        Map<String, List> params = new HashMap<>();

        params.put("medialist", mediaList);
        return new ModelAndView(params, "book/index");
    }
}
