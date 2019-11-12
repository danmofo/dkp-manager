package com.dmoffat.dkpmanager.service;

import com.dmoffat.dkpmanager.dao.PlayerDao;
import com.dmoffat.dkpmanager.model.forms.ForgottenPasswordForm;
import com.dmoffat.dkpmanager.util.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForgottenPasswordService {

    @Autowired private PlayerDao playerDao;

    public void handle(ForgottenPasswordForm form) {
        // do stuff - send email, etc.
    }

    public String generateToken() {
        return RandomStringGenerator.generate();
    }

}
