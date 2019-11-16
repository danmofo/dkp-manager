package com.dmoffat.dkpmanager.controller;

import com.dmoffat.dkpmanager.service.GuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guild-management/")
public class GuildManagementController {

    @Autowired private GuildService guildService;

    @GetMapping("edit")
    public String edit() {
        return "guild-management/edit";
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
