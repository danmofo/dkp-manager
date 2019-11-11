package com.dmoffat.dkpmanager.controller;

import com.dmoffat.dkpmanager.model.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

@Controller
public class LoginController {

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @PostMapping("logout")
    public String logout(@RequestAttribute Session session) {
        session.removeData("playerId");
        return "redirect:/";
    }

}
