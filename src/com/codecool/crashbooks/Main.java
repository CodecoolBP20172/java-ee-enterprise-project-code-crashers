package com.codecool.crashbooks;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

import com.codecool.crashbooks.ORM.PopulateData;
import com.codecool.crashbooks.controller.MediaController;
import com.codecool.crashbooks.controller.MemberController;
import com.codecool.crashbooks.service.*;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CrashBooks");

        MemberService memberService = new MemberService(emf);
        MediaService mediaService = new MediaService(emf);
        AuthorService authorService = new AuthorService(emf);
        CategoryService categoryService = new CategoryService(emf);
        GenreService genreService = new GenreService(emf);

        MediaController mediaController = new MediaController(mediaService, genreService, categoryService);
        MemberController memberController = new MemberController(memberService);

        //Populate Data
        PopulateData.populateDB(emf);

        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        get("/hello", (req, res) -> "Hello Crashers!!!");

        get("/", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(mediaController.renderAllMedia(req, res));
        });

        get("/filter", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(mediaController.renderMediaByFilter(req, res));
        });

        get("/login", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(memberController.loginPage(req, res));
        });

        post("/login", (Request req, Response res) ->{
            return new ThymeleafTemplateEngine().render(memberController.loginLogic(req, res));
        });

        get("/registration", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(memberController.registrationPage(req, res));
        });

        post("/registration", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(memberController.registrationLogic(req, res));
        });

        get("/logout", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(memberController.logout(req, res));
        });

        get("/profile", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(mediaController.renderProfile(req, res));
        });

        post("/soon", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(mediaController.soon(req, res));
        });

        post("/rent", (Request req,Response res) ->{
            return new ThymeleafTemplateEngine().render(mediaController.renderRent(req, res));
        });

        enableDebugScreen();
        //TODO all route should check status code 200 if not should redirect to an error page.
    }


}
