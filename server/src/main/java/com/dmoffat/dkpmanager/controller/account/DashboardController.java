package com.dmoffat.dkpmanager.controller.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("dashboard")
    public String dashboard() {
        return "account/dashboard";
    }

}
