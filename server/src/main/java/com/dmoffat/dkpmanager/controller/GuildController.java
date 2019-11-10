package com.dmoffat.dkpmanager.controller;

import com.dmoffat.dkpmanager.service.GuildService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GuildController {

    private static final Logger logger = LogManager.getLogger(GuildController.class);

    @Autowired
    private GuildService guildService;

    @GetMapping("guilds")
    public String list(Model m) {
        logger.debug("Listing guilds...");
        m.addAttribute("guilds", guildService.list());
        return "list-guilds";
    }

}
