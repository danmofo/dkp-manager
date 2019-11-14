package com.dmoffat.dkpmanager.service;

import com.dmoffat.dkpmanager.dao.GuildDao;
import com.dmoffat.dkpmanager.dao.PlayerDao;
import com.dmoffat.dkpmanager.dao.WowClassDao;
import com.dmoffat.dkpmanager.model.Player;
import com.dmoffat.dkpmanager.model.forms.LoginForm;
import com.dmoffat.dkpmanager.model.forms.SignupForm;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Autowired private ForgottenPasswordService forgottenPasswordService;
    @Autowired private GuildDao guildDao;
    @Autowired private PlayerDao playerDao;
    @Autowired private WowClassDao wowClassDao;

    public Player findById(Integer playerId) {
        return playerDao.find(playerId);
    }

    /**
     * @return The newly created Player.
     */
    public Player signup(SignupForm form) {

        Player player = new Player();
        player.setEmail(form.getEmail());
        player.setPassword(BCrypt.hashpw(form.getPassword(), BCrypt.gensalt()));
        player.setName(form.getCharacterName());
        player.setWowClass(wowClassDao.getProxy(form.getWowClassId()));
        player.setGuild(guildDao.getProxy(form.getGuildId()));
        player.setForgottenPasswordToken(forgottenPasswordService.generateToken());
        player.setDkp(0d);

        playerDao.add(player);

        return player;
    }

    public Player authenticate(LoginForm form) {
        // Check email
        Player player = playerDao.findByEmail(form.getEmail());
        if(player == null) {
            return null;
        }

        // Check password
        if(!BCrypt.checkpw(form.getPassword(), player.getPassword())) {
            return null;
        }

        return player;
    }

    public Player findByForgottenPasswordToken(String token) {
        return playerDao.findByForgottenPasswordToken(token);
    }

    public void changePassword(Integer playerId, String password) {
        Player player = playerDao.find(playerId);
        player.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        player.setForgottenPasswordToken(forgottenPasswordService.generateToken());
        playerDao.update(player);
    }
}
