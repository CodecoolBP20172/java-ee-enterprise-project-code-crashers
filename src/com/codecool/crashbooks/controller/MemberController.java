package com.codecool.crashbooks.controller;

import com.codecool.crashbooks.model.Member;
import com.codecool.crashbooks.tools.Password;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.HashMap;
import java.util.Map;

public class MemberController {

    private MemberController() {
    }

    private static class LazyHolder {
        static final MemberController INSTANCE = new MemberController();
    }

    public static MemberController getInstance() {
        return LazyHolder.INSTANCE;
    }

    public ModelAndView loginPage(Request req, Response res) {
        return new ModelAndView(new HashMap<>(), "book/login");
    }

    public ModelAndView loginLogic(Request req, Response res, EntityManagerFactory emf, MediaController mediaController) {
        Member member = Member.getMemberByName(emf, req.queryParams("name"));
        if (member != null && Password.checkPassword(req.queryParams("password"), member.getPassword())) {
            req.session(true);
            req.session().attribute("name", member.getName());
            req.session().attribute("id", member.getId());
            return mediaController.renderAllBooks(req, res, emf);  //TODO stays on the /login
        } else {
            return errorPage(req, res, "Login Failed! User or Password Invalid!");
        }
    }

    public ModelAndView registrationPage(Request req, Response res) {
        return new ModelAndView(new HashMap<>(), "book/registration");
    }

    public ModelAndView registrationLogic(Request req, Response res, EntityManagerFactory emf, MediaController mediaController) {
        if (memberNameIsNotTaken(emf, req.queryParams("name"))) {
            saveMember(req, emf);
            Member member = Member.getMemberByName(emf, req.queryParams("name"));
            req.session(true);
            req.session().attribute("name",req.queryParams("name"));
            req.session().attribute("id", member.getId());
            return mediaController.renderAllBooks(req, res, emf);  //TODO stays on the /registration
        } else {
            return errorPage(req, res, "Registration failed!");
        }
    }

    public void saveMember(Request req, EntityManagerFactory emf) {
        Member.saveMember(emf, req.queryParams("name"), Password.hashPassword(req.queryParams("password")));
    }

    public boolean memberNameIsNotTaken(EntityManagerFactory emf, String name) {
        return (Member.getMemberByName(emf, name) == null);
    }

    public ModelAndView errorPage(Request req, Response res, String errorMessage) {
        Map params = new HashMap();
        params.put("error", errorMessage);
        return new ModelAndView(params, "book/error");
    }
}
