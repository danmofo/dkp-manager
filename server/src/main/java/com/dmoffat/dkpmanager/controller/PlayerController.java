package com.dmoffat.dkpmanager.controller;

import com.dmoffat.dkpmanager.model.Player;
import com.dmoffat.dkpmanager.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("players/{playerId}")
    public String viewPlayer(@PathVariable Integer playerId, Model m) {
        Player player = playerService.findById(playerId);
        if(player == null) {
            return "redirect:/404";
        }

        m.addAttribute("player", player);
        return "view-player";
    }

}
