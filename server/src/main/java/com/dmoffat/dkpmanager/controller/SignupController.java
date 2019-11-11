package com.dmoffat.dkpmanager.controller;

import com.dmoffat.dkpmanager.model.forms.SignupForm;
import com.dmoffat.dkpmanager.model.forms.ValidationErrors;
import com.dmoffat.dkpmanager.model.json.JsonResponse;
import com.dmoffat.dkpmanager.service.GuildService;
import com.dmoffat.dkpmanager.service.PlayerService;
import com.dmoffat.dkpmanager.service.WowClassService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class SignupController {
    private static final Logger logger = LogManager.getLogger(SignupController.class);

    @Autowired private MessageSource messageSource;
    @Autowired private PlayerService playerService;
    @Autowired private GuildService guildService;
    @Autowired private WowClassService wowClassService;

    @GetMapping("signup")
    public String signup(Model m) {
        m.addAttribute("signupForm", new SignupForm());
        m.addAttribute("guilds", guildService.list());
        m.addAttribute("classes", wowClassService.list());
        return "signup";
    }

    @PostMapping("signup")
    @ResponseBody
    public JsonResponse handleSignup(@Valid SignupForm signupForm, BindingResult result, Model m) {
        logger.debug(signupForm);

        if(result.hasErrors()) {
            logger.debug("There were validation errors in the form.");
            return new JsonResponse(new ValidationErrors(result, messageSource));
        }

        // todo: Handle duplicate email addresses.
        playerService.signup(signupForm);

        return new JsonResponse(true);
    }

}
