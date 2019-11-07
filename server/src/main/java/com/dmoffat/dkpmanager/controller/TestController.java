package com.dmoffat.dkpmanager.controller;

import com.dmoffat.dkpmanager.model.Guild;
import com.dmoffat.dkpmanager.service.GuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired
    private GuildService guildService;

    @GetMapping("test")
    @ResponseBody
    public String test() {
        Guild guild = guildService.findById(1);
        System.out.println(guild);
        return "{ \"success\":\"true\" }";
    }

}
