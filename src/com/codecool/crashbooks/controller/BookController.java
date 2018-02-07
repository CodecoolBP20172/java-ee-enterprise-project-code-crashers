package com.codecool.crashbooks.controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codecool.crashbooks.model.Book;

public class BookController {


    public static ModelAndView renderAllBooks(Request request, Response response) {
        List<Book> bookList = new ArrayList<>();
        Book book1 = new Book(1,"testtitle");
        Book book2 = new Book(2,"testtitle2");
        bookList.add(book1);
        bookList.add(book2);
        Map<String, List> params = new HashMap<>();

        params.put("booklist", bookList);
        return new ModelAndView(params, "book/index");
    }
}
