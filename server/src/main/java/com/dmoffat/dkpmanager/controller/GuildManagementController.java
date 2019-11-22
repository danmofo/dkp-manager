package com.dmoffat.dkpmanager.controller;

import com.dmoffat.dkpmanager.model.Session;
import com.dmoffat.dkpmanager.model.UnitName;
import com.dmoffat.dkpmanager.model.forms.*;
import com.dmoffat.dkpmanager.model.json.JsonResponse;
import com.dmoffat.dkpmanager.service.DkpDecayIntervalService;
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
import java.text.DecimalFormat;
import java.util.Locale;

@Controller
@RequestMapping("/guild-management/")
public class GuildManagementController {

    @Autowired private DkpDecayIntervalService dkpDecayIntervalService;
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
    public String awardDkp(@RequestAttribute Session session, Model m) {
        m.addAttribute("awardDkpForm", new AwardDkpForm());
        m.addAttribute("guild", guildService.findById(session.getPlayerGuildId()));
        return "guild-management/award-dkp";
    }

    @PostMapping("award-dkp")
    @ResponseBody
    public JsonResponse handleAwardDkp(@Valid AwardDkpForm awardDkpForm, BindingResult result,
                                       @RequestAttribute Session session, HttpServletResponse resp) {

        if(result.hasErrors()) {
            return new JsonResponse(new ValidationErrors(result, messageSource));
        }

        guildService.awardDkp(session.getPlayer().getGuild(), awardDkpForm);

        session.addData("message", messageSource.getMessage("guild-award-dkp-success", null, Locale.UK));
        resp.addCookie(sessionService.createSessionCookie(session));

        return new JsonResponse(true).addPayload("redirectUrl", "/guild-management/award-dkp");
    }

    @GetMapping("decay-dkp")
    public String decayDkp(Model m, @RequestAttribute Session session) {
        m.addAttribute("guild", guildService.findById(session.getPlayerGuildId()));
        m.addAttribute("addDkpDecayIntervalForm", new AddDkpDecayIntervalForm());
        m.addAttribute("dkpDecayIntervalUnitNames", UnitName.values());
        return "guild-management/decay-dkp";
    }

    @PostMapping("decay-dkp")
    @ResponseBody
    public JsonResponse handleDecayDkp(@RequestBody @Valid DecayDkpForm decayDkpForm, BindingResult result,
                                       @RequestAttribute Session session) {

        if(result.hasErrors()) {
            return new JsonResponse(new ValidationErrors(result, messageSource));
        }

        Double newDkpValue = guildService.decayDkp(session.getPlayer(), decayDkpForm);
        if(newDkpValue == null) {
            result.rejectValue("amount", "guild-decay-dkp-not-allowed");
            return new JsonResponse(new ValidationErrors(result, messageSource));
        }

        DecimalFormat format = new DecimalFormat("#,##0.##");

        return new JsonResponse(true).addPayload("newDkpValue", format.format(newDkpValue));
    }

    @PostMapping("add-dkp-decay-interval")
    @ResponseBody
    public JsonResponse handleAddDecayDkpInterval(@Valid AddDkpDecayIntervalForm addDkpDecayIntervalForm, BindingResult result,
                                                  @RequestAttribute Session session) {

        if(result.hasErrors()) {
            return new JsonResponse(new ValidationErrors(result, messageSource));
        }

        dkpDecayIntervalService.addDecayDkpInterval(session.getPlayer().getGuild(), addDkpDecayIntervalForm);

        return new JsonResponse(true)
                .addPayload("redirectUrl", "/guild-management/decay-dkp");
    }

    @PostMapping("delete-dkp-decay-interval")
    @ResponseBody
    public JsonResponse handleDeleteDecayDkpInterval(@RequestAttribute Session session) {

        dkpDecayIntervalService.deleteDkpDecayInterval(session.getPlayer().getGuild());

        return new JsonResponse(true);
    }
    
}
