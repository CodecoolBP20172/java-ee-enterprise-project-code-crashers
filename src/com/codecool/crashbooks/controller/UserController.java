package com.codecool.crashbooks.controller;

import com.codecool.crashbooks.model.Membership;
import com.codecool.crashbooks.model.User;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.text.html.parser.Entity;
import java.util.HashMap;
import java.util.Map;

public class UserController {

    public static ModelAndView login(Request req, Response res) {
        return new ModelAndView(new HashMap<>(), "book/login");
    }

    public static ModelAndView registration(Request req, Response res) {
        return new ModelAndView(new HashMap<>(), "book/registration");
    }

    public static User saveUser(Request req, EntityManagerFactory emf) {
        User user = new User(req.queryParams("name"), req.queryParams("password"));
        User.saveUser(emf, user);
        return user;
    }

    public static boolean userNameIsValid(EntityManagerFactory emf, String name) {
        return User.getUserByName(emf, name) == null;
    }

    public static ModelAndView errorPage(Request req, Response res, String errorMessage) {
        Map params = new HashMap();
        params.put("error", errorMessage);
        return new ModelAndView(params, "error");
    }


}
