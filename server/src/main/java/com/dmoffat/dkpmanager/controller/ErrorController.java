package com.dmoffat.dkpmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("error")
    public String error() {
        return "error";
    }

    @GetMapping("404")
    public String pageNotFound() {
        return "404";
    }

}
