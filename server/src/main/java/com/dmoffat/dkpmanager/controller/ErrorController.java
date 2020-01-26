package com.dmoffat.dkpmanager.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    private static final Logger logger = LogManager.getLogger(ErrorController.class);

    @GetMapping("/error")
    public String error(HttpServletRequest req) {

        Integer statusCode = (Integer)req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if(statusCode == 404) {
            return "forward:/404";
        }

        return "error";
    }

    @GetMapping("/404")
    public String pageNotFound(Model m, HttpServletRequest req) {
        m.addAttribute("pageName", req.getRequestURI());
        return "404";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
