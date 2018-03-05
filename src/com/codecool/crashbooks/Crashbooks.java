package com.codecool.crashbooks;

import com.codecool.crashbooks.ORM.PopulateData;
import com.codecool.crashbooks.controller.MediaController;
import com.codecool.crashbooks.controller.MemberController;
import com.codecool.crashbooks.controller.RentController;
import com.codecool.crashbooks.service.*;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Crashbooks {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CrashBooks");

        MemberService memberService = new MemberService(emf);
        MediaService mediaService = new MediaService(emf);
        CategoryService categoryService = new CategoryService(emf);
        GenreService genreService = new GenreService(emf);
        CopyService copyService = new CopyService(emf);
        RentService rentService = new RentService(emf, copyService);

        MediaController mediaController = new MediaController(mediaService, genreService, categoryService, rentService);
        MemberController memberController = new MemberController(memberService);
        RentController rentController = new RentController(rentService, memberService, copyService);

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
            return new ThymeleafTemplateEngine().render(memberController.renderLoginPage(req, res));
        });

        post("/login", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(memberController.login(req, res));
        });

        get("/registration", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(memberController.renderRegistrationPage(req, res));
        });

        post("/registration", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(memberController.register(req, res));
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

        post("/rent", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(rentController.rentCopy(req, res));
        });

        enableDebugScreen();
        //TODO all route should check status code 200 if not should redirect to an error page.
    }

}
