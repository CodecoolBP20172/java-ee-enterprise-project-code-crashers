package com.codecool.crashbooks.controller;

import com.codecool.crashbooks.model.AllUsers;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.HashMap;
import java.util.Map;

public class UserController {

    public static ModelAndView login(Request req, Response res) {
        return new ModelAndView(new HashMap<>(), "book/login");
    }

    public static ModelAndView registration(Request req, Response res) {
        return new ModelAndView(new HashMap<>(), "book/registration");
    }

    public static void saveUser(Request req, EntityManagerFactory emf) {
        AllUsers.saveUser(emf, req.queryParams("name"), req.queryParams("password"));
    }

    public static boolean userNameIsValid(EntityManagerFactory emf, String name) {
        try {
            AllUsers.getUserByName(emf, name).getName();
        } catch (NoResultException e) {
            return true;
        }
        return false;
    }

    public static ModelAndView errorPage(Request req, Response res, String errorMessage) {
        Map params = new HashMap();
        params.put("error", errorMessage);
        return new ModelAndView(params, "book/error");
    }


}
