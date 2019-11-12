package com.dmoffat.dkpmanager.controller;

import com.dmoffat.dkpmanager.model.Session;
import com.dmoffat.dkpmanager.model.forms.ForgottenPasswordForm;
import com.dmoffat.dkpmanager.model.forms.ValidationErrors;
import com.dmoffat.dkpmanager.model.json.JsonResponse;
import com.dmoffat.dkpmanager.service.ForgottenPasswordService;
import com.dmoffat.dkpmanager.service.PlayerService;
import com.dmoffat.dkpmanager.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Locale;

@Controller
public class ForgottenPasswordController {

    @Autowired private ForgottenPasswordService forgottenPasswordService;
    @Autowired private MessageSource messageSource;
    @Autowired private PlayerService playerService;
    @Autowired private SessionService sessionService;

    @GetMapping("forgotten-password")
    public String forgottenPassword(Model m, @RequestAttribute Session session) {
        m.addAttribute("forgottenPasswordForm", new ForgottenPasswordForm());
        m.addAttribute("message", session.getMessage());
        return "forgotten-password";
    }

    @PostMapping("forgotten-password")
    @ResponseBody
    public JsonResponse handleForgottenPassword(@Valid ForgottenPasswordForm forgottenPasswordForm, BindingResult result,
                                                @RequestAttribute Session session, HttpServletResponse resp) {

        if(result.hasErrors()) {
            return new JsonResponse(new ValidationErrors(result, messageSource));
        }

        forgottenPasswordService.handle(forgottenPasswordForm);

        session.addData("message", messageSource.getMessage("forgotten-password-email-sent", null, Locale.UK));
        resp.addCookie(sessionService.createSessionCookie(session));

        return new JsonResponse(true).addPayload("redirectUrl", "/forgotten-password");
    }

    // /reset-password?token=<some random string we generate>
    @GetMapping("reset-password")
    public String resetPassword(@RequestParam(required = false) String token, Model m) {
        m.addAttribute("player", playerService.findByForgottenPasswordToken(token));
        m.addAttribute("token", token);
        return "reset-password";
    }
}
