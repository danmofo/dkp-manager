package com.dmoffat.dkpmanager.service;

import com.dmoffat.dkpmanager.dao.PlayerDao;
import com.dmoffat.dkpmanager.model.Player;
import com.dmoffat.dkpmanager.model.forms.ForgottenPasswordForm;
import com.dmoffat.dkpmanager.util.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ForgottenPasswordService {

    @Autowired private EmailService emailService;
    @Autowired private PlayerDao playerDao;

    public enum HandleResponse {
        SUCCESS,
        EMAIL_NOT_SENT,
        EMAIL_DOESNT_EXIST
    }

    // todo: Think of a better name
    public HandleResponse handle(ForgottenPasswordForm form) {
        Player player = playerDao.findByEmail(form.getEmail());
        if(player == null) {
            return HandleResponse.EMAIL_DOESNT_EXIST;
        }

        Map<String, String> templateData = new HashMap<>();
        templateData.put("resetPasswordLink", getResetPasswordUrl(player.getForgottenPasswordToken()));
        boolean wasEmailSent = emailService.send("Forgotten password", form.getEmail(), templateData);
        return wasEmailSent ? HandleResponse.SUCCESS : HandleResponse.EMAIL_NOT_SENT;
    }

    private String getResetPasswordUrl(String token) {
        return "http://localhost:8080/reset-password?token=" + token;
    }

    public String generateToken() {
        return RandomStringGenerator.generate();
    }

}
