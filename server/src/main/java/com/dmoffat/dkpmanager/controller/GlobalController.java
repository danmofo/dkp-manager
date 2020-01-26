package com.dmoffat.dkpmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Define global data here which is added to EVERY model.
 */
@ControllerAdvice
public class GlobalController {

    @Autowired
    private Environment environment;

    @ModelAttribute
    public void addGlobalData(Model m) {
        m.addAttribute("environment", environment.getProperty("environment"));
    }

}
