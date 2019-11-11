package com.dmoffat.dkpmanager.controller;

import com.dmoffat.dkpmanager.model.Player;
import com.dmoffat.dkpmanager.model.Session;
import com.dmoffat.dkpmanager.model.forms.LoginForm;
import com.dmoffat.dkpmanager.model.forms.ValidationErrors;
import com.dmoffat.dkpmanager.model.json.JsonResponse;
import com.dmoffat.dkpmanager.service.PlayerService;
import com.dmoffat.dkpmanager.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired private MessageSource messageSource;
    @Autowired private PlayerService playerService;
    @Autowired private SessionService sessionService;

    @GetMapping("login")
    public String login(Model m) {
        m.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("login")
    @ResponseBody
    public JsonResponse handleLogin(@Valid LoginForm loginForm, BindingResult result,
                                    @RequestAttribute Session session, HttpServletResponse resp) {

        if(result.hasErrors()) {
            return new JsonResponse(new ValidationErrors(result, messageSource));
        }

        Player player = playerService.authenticate(loginForm);
        if(player == null) {
            // Player not found in the database OR the password was incorrect.
            result.rejectValue("email", "InvalidCredentials");
            return new JsonResponse(new ValidationErrors(result, messageSource));
        }

        // Their details were correct, and they are authenticated!
        session.addData("playerId", player.getId());
        resp.addCookie(sessionService.createSessionCookie(session));

        return new JsonResponse(true).addPayload("redirectUrl", "/dashboard");
    }

    @PostMapping("logout")
    public String logout(@RequestAttribute Session session) {
        session.removeData("playerId");
        return "redirect:/";
    }

}
