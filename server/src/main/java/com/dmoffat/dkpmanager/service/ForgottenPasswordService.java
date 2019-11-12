package com.dmoffat.dkpmanager.service;

import com.dmoffat.dkpmanager.dao.PlayerDao;
import com.dmoffat.dkpmanager.model.forms.ForgottenPasswordForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForgottenPasswordService {

    @Autowired private PlayerDao playerDao;

    public void handle(ForgottenPasswordForm form) {
        // do stuff - send email, etc.


    }

}
