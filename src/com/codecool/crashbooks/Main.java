package com.codecool.crashbooks;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

import com.codecool.crashbooks.ORM.PopulateData;
import com.codecool.crashbooks.controller.BookController;
import com.codecool.crashbooks.controller.UserController;
import com.codecool.crashbooks.model.User;
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

        get("/hello", (req, res) -> "Hello Crashers!!!");

        get("/", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(BookController.renderAllBooks(req, res, emf));
        });

        get("/filter", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(BookController.renderBooksByFilter(req, res, emf));
        });

        get("/login", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(UserController.login(req, res));
        });

//        post("/login", (Request req, Response res) ->{
//            String name = req.queryParams("name");
//            String password = req.queryParams("password");
//            if (UserController.loginIsValid(emf, name, password)) {
//                User user = User.getUserByName(emf, name);
//                req.session(true);
//                req.session().attribute("name",req.queryParams("name"));
//                req.session().attribute("id", user.getId());
//                res.redirect("/");
//            } else {
//                return new ThymeleafTemplateEngine().render(UserController.login(req, res, "wrong email/pw"));
//            }
//            return null;
//
//        });
        get("/registration", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(UserController.registration(req, res));
        });

        post("/registration", (Request req, Response res) -> {
            if (UserController.userNameIsValid(emf, req.queryParams("name"))) {
                User user = UserController.saveUser(req, emf);
                req.session(true);
                req.session().attribute("name",req.queryParams("name"));
                req.session().attribute("id", user.getId());
                res.redirect("/");
            } else {
                return new ThymeleafTemplateEngine().render(UserController.login(req, res, "wrong email/pw"));
            }
            return null;
        });

        enableDebugScreen();
    }


}
