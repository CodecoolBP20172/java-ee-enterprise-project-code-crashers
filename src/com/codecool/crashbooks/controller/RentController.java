package com.codecool.crashbooks.controller;

import com.codecool.crashbooks.service.CopyService;
import com.codecool.crashbooks.service.MemberService;
import com.codecool.crashbooks.service.RentService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class RentController {

    private final RentService rentService;
    private final MemberService memberService;
    private final CopyService copyService;

    public RentController(RentService rentService, MemberService memberService, CopyService copyService) {
        this.rentService = rentService;
        this.memberService = memberService;
        this.copyService = copyService;
    }

    public ModelAndView renderRent(Request request, Response response) {
        Map<String, Object> params = new HashMap<>();
        if (request.session().attribute("name") != null) {
            rentService.createNewRent(memberService.getMemberByName(request.session().attribute("name")), copyService.getFirstAvailableCopy(Integer.parseInt(request.queryParams("media_id"))));
        } else {
            response.redirect("/");
        }
        response.redirect("/");
        params.put("member", request.session().attribute("name"));
        return new ModelAndView(params, "book/index");
    }
}
