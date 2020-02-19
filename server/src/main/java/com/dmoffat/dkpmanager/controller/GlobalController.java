package com.dmoffat.dkpmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * Define global data here which is added to EVERY model.
 */
@ControllerAdvice
public class GlobalController {

    @Autowired
    private Environment environment;

    @ModelAttribute
    public void addGlobalData(Model m, HttpServletRequest req) {
        m.addAttribute("environment", environment.getProperty("environment"));
        m.addAttribute("pageName", derivePageName(req));
    }

    private String derivePageName(HttpServletRequest req) {
        String uri = req.getRequestURI().replaceFirst("/", "");

        if(uri.equals("")) {
            return "home";
        }

        // todo: how do we handle nested paths? We should probably retain all paths and join them with a character and
        // remove everything after the final slash.
        // e.g. /guilds/dans-guild would become guilds
        //      /players/priests/dan would become players-priests

        return uri;
    }

}
