package com.dmoffat.dkpmanager.service;

import com.dmoffat.dkpmanager.dao.GuildDao;
import com.dmoffat.dkpmanager.dao.PlayerDao;
import com.dmoffat.dkpmanager.dao.WowClassDao;
import com.dmoffat.dkpmanager.model.Player;
import com.dmoffat.dkpmanager.model.forms.SignupForm;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

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
        player.setDkp(0d);

        playerDao.add(player);

        return player;
    }

}
