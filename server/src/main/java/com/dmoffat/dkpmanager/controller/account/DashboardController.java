package com.dmoffat.dkpmanager.controller.account;

import com.dmoffat.dkpmanager.model.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

@Controller
public class DashboardController {

    @GetMapping("dashboard")
    public String dashboard(Model m, @RequestAttribute Session session) {
        return "account/dashboard";
    }

}
