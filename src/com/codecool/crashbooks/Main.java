package com.codecool.crashbooks;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

import com.codecool.crashbooks.ORM.PopulateData;
import com.codecool.crashbooks.controller.MediaController;
import com.codecool.crashbooks.controller.MemberController;
import com.codecool.crashbooks.model.Member;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CrashBooks");

        MemberController memberController = MemberController.getInstance();

        //Populate Data
        PopulateData.populateDB(emf);

        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        get("/hello", (req, res) -> "Hello Crashers!!!");

        get("/", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(MediaController.renderAllBooks(req, res, emf));
        });

        get("/filter", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(MediaController.renderBooksByFilter(req, res, emf));
        });

        get("/login", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(memberController.login(req, res));
        });

        post("/login", (Request req, Response res) ->{
            Member member = Member.getMemberByName(emf, req.queryParams("name"));
            if (member.getPassword().equals(req.queryParams("password"))) {
                req.session(true);
                req.session().attribute("name", member.getName());
                req.session().attribute("id", member.getId());
                res.redirect("/");
            } else {
                return new ThymeleafTemplateEngine().render(memberController.errorPage(req, res, "Login Failed!"));
            }
            return null;

        });
        get("/registration", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(memberController.registration(req, res));
        });

        post("/registration", (Request req, Response res) -> {
            System.out.println(req.queryParams("name"));
            if (memberController.memberNameIsValid(emf, req.queryParams("name"))) {
                memberController.saveMember(req, emf);
                Member member = Member.getMemberByName(emf, req.queryParams("name"));
                req.session(true);
                req.session().attribute("name",req.queryParams("name"));
                req.session().attribute("id", member.getId());
                res.redirect("/");
            } else {
                return new ThymeleafTemplateEngine().render(memberController.errorPage(req, res, "Registration failed!"));
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
            return new ThymeleafTemplateEngine().render(MediaController.soon(req, res));
        });

        post("/soon", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(MediaController.soon(req, res));
        });
        enableDebugScreen();
        //TODO all route should check status code 200 if not should redirect to an error page.
    }


}
