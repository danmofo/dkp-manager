package com.dmoffat.dkpmanager.controller;

import com.dmoffat.dkpmanager.model.Guild;
import com.dmoffat.dkpmanager.model.pagination.PlayerParameters;
import com.dmoffat.dkpmanager.model.pagination.Results;
import com.dmoffat.dkpmanager.service.GuildService;
import com.dmoffat.dkpmanager.service.PlayerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewGuildInfoController {
    private static final Logger logger = LogManager.getLogger(ViewGuildInfoController.class);

    @Autowired private GuildService guildService;
    @Autowired private PlayerService playerService;

    @GetMapping("guilds")
    public String list(Model m) {
        logger.debug("Listing guilds...");
        m.addAttribute("guildResults", guildService.list(1));
        return "list-guilds";
    }

    @GetMapping("guilds/ajax")
    public String listGuildsAjax(Results.Parameters parameters, Model m) {
        m.addAttribute("guildResults", guildService.list(parameters.getPage()));
        return "guild-results";
    }

    @GetMapping("guilds/{guildUri}")
    public String viewGuildByUri(@PathVariable String guildUri, Model m) {
        logger.debug("Viewing guild page for..." + guildUri);

        Guild guild = guildService.findByUri(guildUri);
        if(guild == null) {
            return "redirect:/404";
        }

        m.addAttribute("guild", guild);
        return "view-guild";
    }

    @GetMapping("players/ajax")
    public String listPlayersAjax(PlayerParameters parameters, Model m) {
        m.addAttribute("playerResults", playerService.findByGuildId(parameters.getGuildId(), parameters.getPage()));
        return "player-results";
    }

}
