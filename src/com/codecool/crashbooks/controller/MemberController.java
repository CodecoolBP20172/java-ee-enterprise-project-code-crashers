package com.codecool.crashbooks.controller;

import com.codecool.crashbooks.model.Member;
import com.codecool.crashbooks.service.MemberService;
import com.codecool.crashbooks.utility.Password;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    public ModelAndView renderLoginPage(Request req, Response res) {
        return new ModelAndView(new HashMap<>(), "book/login");
    }

    public ModelAndView login(Request req, Response res) {
        Member member = memberService.getMemberByName(req.queryParams("name"));
        if (member != null && Password.checkPassword(req.queryParams("password"), member.getPassword())) {
            req.session(true);
            req.session().attribute("name", member.getName());
            req.session().attribute("id", member.getId());
            res.redirect("/");
        } else {
            return renderErrorPage(req, res, "Login Failed! User or Password Invalid!");
        }
        return null;
    }

    public ModelAndView renderRegistrationPage(Request req, Response res) {
        return new ModelAndView(new HashMap<>(), "book/registration");
    }

    public ModelAndView register(Request req, Response res) {
        if (checkIsMemberNameFree(req.queryParams("name"))) {
            saveMember(req);
            Member member = memberService.getMemberByName(req.queryParams("name"));
            req.session(true);
            req.session().attribute("name", req.queryParams("name"));
            req.session().attribute("id", member.getId());
            res.redirect("/");
        } else {
            return renderErrorPage(req, res, "Registration failed!");
        }
        return null;
    }

    private void saveMember(Request req) {
        memberService.saveMember(req.queryParams("name"), Password.hashPassword(req.queryParams("password")));
    }

    private boolean checkIsMemberNameFree(String name) {
        return (memberService.getMemberByName(name) == null);
    }

    private ModelAndView renderErrorPage(Request req, Response res, String errorMessage) {
        Map params = new HashMap();
        params.put("error", errorMessage);
        return new ModelAndView(params, "book/error");
    }

    public ModelAndView logout(Request req, Response res) {
        req.session().removeAttribute("name");
        req.session().removeAttribute("id");
        res.redirect("/");
        return null;
    }
}
