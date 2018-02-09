package com.codecool.crashbooks;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

import com.codecool.crashbooks.ORM.PopulateData;
import com.codecool.crashbooks.controller.BookController;
import com.codecool.crashbooks.controller.UserController;
import com.codecool.crashbooks.model.AllUsers;
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

        post("/login", (Request req, Response res) ->{
            AllUsers user = AllUsers.getUserByName(emf, req.queryParams("name"));
            if (user.getPassword().equals(req.queryParams("password"))) {
                req.session(true);
                req.session().attribute("name", user.getName());
                req.session().attribute("id", user.getId());
                res.redirect("/");
            } else {
                return new ThymeleafTemplateEngine().render(UserController.errorPage(req, res, "Login Failed!"));
            }
            return null;

        });
        get("/registration", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(UserController.registration(req, res));
        });

        post("/registration", (Request req, Response res) -> {
            System.out.println(req.queryParams("name"));
            if (UserController.userNameIsValid(emf, req.queryParams("name"))) {
                UserController.saveUser(req, emf);
                AllUsers user = AllUsers.getUserByName(emf, req.queryParams("name"));
                req.session(true);
                req.session().attribute("name",req.queryParams("name"));
                req.session().attribute("id", user.getId());
                res.redirect("/");
            } else {
                return new ThymeleafTemplateEngine().render(UserController.errorPage(req, res, "Registration failed!"));
            }
            return "";
        });

        get("/logout", (Request req, Response res) -> {
            req.session().removeAttribute("name");
            req.session().removeAttribute("id");
            res.redirect("/");
            return null;
        });

        get("/soon", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(BookController.soon(req, res));
        });

        post("/soon", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(BookController.soon(req, res));
        });
        enableDebugScreen();
        //TODO all route should check status code 200 if not should redirect to an error page.
    }


}
