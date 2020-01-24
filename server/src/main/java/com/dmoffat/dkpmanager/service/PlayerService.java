package com.dmoffat.dkpmanager.service;

import com.dmoffat.dkpmanager.dao.DkpHistoryDao;
import com.dmoffat.dkpmanager.dao.GuildDao;
import com.dmoffat.dkpmanager.dao.PlayerDao;
import com.dmoffat.dkpmanager.dao.WowClassDao;
import com.dmoffat.dkpmanager.model.DkpHistory;
import com.dmoffat.dkpmanager.model.Player;
import com.dmoffat.dkpmanager.model.forms.LoginForm;
import com.dmoffat.dkpmanager.model.forms.SignupForm;
import com.dmoffat.dkpmanager.model.pagination.PlayerParameters;
import com.dmoffat.dkpmanager.model.pagination.Results;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired private DkpHistoryDao dkpHistoryDao;
    @Autowired private ForgottenPasswordService forgottenPasswordService;
    @Autowired private GuildDao guildDao;
    @Autowired private PlayerDao playerDao;
    @Autowired private WowClassDao wowClassDao;

    public Player findById(Integer playerId) {
        return playerDao.find(playerId);
    }

    public Results<DkpHistory> findDkpHistoryByPlayerId(Integer playerId, Integer pageNum) {
        Results.Parameters params = new Results.Parameters();
        params.setPage(pageNum);

        Results<DkpHistory> results = new Results<>(params);
        results.setNumFound(dkpHistoryDao.countByPlayerId(playerId).intValue());
        results.setItems(dkpHistoryDao.findByPlayerId(playerId, params));

        return results;
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

    // todo: Refactor this to use Results, it will still need to return a list of all players in the guild, not just a specific page.
    public List<Player> findByGuildId(Integer guildId) {
        Results.Parameters params = new Results.Parameters();
        params.setItemsPerPage(9999);
        return playerDao.findByGuildId(guildId, params);
    }

    public Results<Player> findByGuildId(Integer guildId, Integer pageNum) {
        PlayerParameters params = new PlayerParameters();
        params.setPage(pageNum);
        params.setGuildId(guildId);

        Results<Player> results = new Results<>(params);
        results.setItems(playerDao.findByGuildId(guildId, params));
        results.setNumFound(playerDao.countByGuildId(guildId).intValue());

        return results;
    }

}
