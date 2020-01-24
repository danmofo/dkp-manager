package com.dmoffat.dkpmanager.controller;

import com.dmoffat.dkpmanager.model.Player;
import com.dmoffat.dkpmanager.model.pagination.DkpHistoryParameters;
import com.dmoffat.dkpmanager.model.pagination.PlayerParameters;
import com.dmoffat.dkpmanager.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PlayerController {

    @Autowired private PlayerService playerService;

    @GetMapping("players/{playerId}")
    public String viewPlayer(@PathVariable Integer playerId, Model m) {
        Player player = playerService.findById(playerId);
        if(player == null) {
            return "redirect:/404";
        }

        m.addAttribute("player", player);
        m.addAttribute("dkpHistoryResults", playerService.findDkpHistoryByPlayerId(playerId, 1));

        return "view-player";
    }

    @GetMapping("dkp-history/ajax")
    public String listPlayersAjax(DkpHistoryParameters parameters, Model m) {
        m.addAttribute("dkpHistoryResults",
                playerService.findDkpHistoryByPlayerId(parameters.getPlayerId(), parameters.getPage()));

        return "dkp-history-results";
    }

}
