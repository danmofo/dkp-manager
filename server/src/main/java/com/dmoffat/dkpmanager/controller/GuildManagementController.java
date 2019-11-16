package com.dmoffat.dkpmanager.controller;

import com.dmoffat.dkpmanager.model.Session;
import com.dmoffat.dkpmanager.model.forms.EditGuildForm;
import com.dmoffat.dkpmanager.model.forms.ValidationErrors;
import com.dmoffat.dkpmanager.model.json.JsonResponse;
import com.dmoffat.dkpmanager.service.GuildService;
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
@RequestMapping("/guild-management/")
public class GuildManagementController {

    @Autowired private GuildService guildService;
    @Autowired private MessageSource messageSource;
    @Autowired private SessionService sessionService;

    @GetMapping("edit")
    public String edit(@RequestAttribute Session session, Model m) {
        m.addAttribute("editGuildForm", new EditGuildForm(session.getPlayer().getGuild()));
        return "guild-management/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    public JsonResponse handleEdit(@Valid EditGuildForm editGuildForm, BindingResult result,
                                   @RequestAttribute Session session, HttpServletResponse resp) {

        if(result.hasErrors()) {
            return new JsonResponse(new ValidationErrors(result, messageSource));
        }

        guildService.edit(session.getPlayer().getGuild(), editGuildForm);
        session.addData("message", messageSource.getMessage("guild-edit-success", null, Locale.UK));
        resp.addCookie(sessionService.createSessionCookie(session));

        return new JsonResponse(true).addPayload("redirectUrl", "/guild-management/edit");
    }

    @GetMapping("award-dkp")
    public String awardDkp() {
        return "guild-management/award-dkp";
    }

    @GetMapping("decay-dkp")
    public String decayDkp() {
        return "guild-management/decay-dkp";
    }

    @GetMapping("edit-dkp")
    public String editDkp() {
        return "guild-management/edit-dkp";
    }

}
