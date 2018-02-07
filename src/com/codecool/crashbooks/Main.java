package com.codecool.crashbooks;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;


public class Main {
    public static void main(String[] args) {

        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        get("/hello", (req, res) -> "Hello World!");

        enableDebugScreen();
    }
}
