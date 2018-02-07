package com.codecool.crashbooks;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

import com.codecool.crashbooks.ORM.PopulateData;
import com.codecool.crashbooks.controller.BookController;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CrashBooks");

        //Populate Data
        PopulateData.populateDB(emf);

        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        get("/hello", (req, res) -> "Hello World!");

        get("/", BookController::renderAllBooks, new ThymeleafTemplateEngine());

        enableDebugScreen();
    }


}
