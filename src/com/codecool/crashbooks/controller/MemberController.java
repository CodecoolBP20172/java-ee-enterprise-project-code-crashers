package com.codecool.crashbooks.controller;

import com.codecool.crashbooks.model.Member;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.HashMap;
import java.util.Map;

public class MemberController {

    public static ModelAndView login(Request req, Response res) {
        return new ModelAndView(new HashMap<>(), "book/login");
    }

    public static ModelAndView registration(Request req, Response res) {
        return new ModelAndView(new HashMap<>(), "book/registration");
    }

    public static void saveMember(Request req, EntityManagerFactory emf) {
        Member.saveMember(emf, req.queryParams("name"), req.queryParams("password"));
    }

    public static boolean memberNameIsValid(EntityManagerFactory emf, String name) {
        try {
            Member.getMemberByName(emf, name).getName();
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
