package com.codecool.crashbooks.controller;

import com.codecool.crashbooks.model.Membership;
import com.codecool.crashbooks.model.User;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.text.html.parser.Entity;
import java.util.HashMap;

public class UserController {

    public static ModelAndView login(Request req, Response res) {
        return new ModelAndView(new HashMap<>(), "login");
    }

    public static ModelAndView registration(Request req, Response res) {
        return new ModelAndView(new HashMap<>(), "registration");
    }

    public static User saveUser(Request req, EntityManagerFactory emf) {
        User user = new User(req.queryParams("name"), req.queryParams("password"));
        User.saveUser(emf, user);
        return user;
    }

    public static boolean userNameIsValid(EntityManagerFactory emf, String name) {
        return User.getUserByName(emf, name) == null;
    }
}
