package com.dmoffat.dkpmanager.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    private static final Logger logger = LogManager.getLogger(ErrorController.class);

    @GetMapping("/test-error")
    public String testError() {
        throw new IllegalArgumentException("This is broken!");
    }

    @GetMapping("/error")
    public String error(DefaultErrorAttributes defaultErrorAttributes, WebRequest webRequest, Model m) {

        Map<String, Object> errorAttributes = defaultErrorAttributes.getErrorAttributes(webRequest, true);
        m.addAllAttributes(errorAttributes);

        Integer statusCode =
                (Integer)webRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE, RequestAttributes.SCOPE_REQUEST);

        if(statusCode != null) {
            if(statusCode == 404) {
                return "forward:/404";
            }
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
