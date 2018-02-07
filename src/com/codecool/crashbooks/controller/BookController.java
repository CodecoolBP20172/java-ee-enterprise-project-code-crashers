package com.codecool.crashbooks.controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class BookController {


    public static ModelAndView renderAllBooks(Request request, Response response) {
        Map params = new HashMap<>();
        return new ModelAndView(params, "book/index");
    }
}
