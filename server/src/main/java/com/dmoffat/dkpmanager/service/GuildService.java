package com.dmoffat.dkpmanager.service;

import com.dmoffat.dkpmanager.dao.DkpHistoryDao;
import com.dmoffat.dkpmanager.dao.GuildDao;
import com.dmoffat.dkpmanager.dao.PlayerDao;
import com.dmoffat.dkpmanager.model.DkpHistory;
import com.dmoffat.dkpmanager.model.Guild;
import com.dmoffat.dkpmanager.model.Player;
import com.dmoffat.dkpmanager.model.forms.AwardDkpForm;
import com.dmoffat.dkpmanager.model.forms.EditGuildForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GuildService {
    private static final Logger logger = LogManager.getLogger(GuildService.class);

    @Autowired private DkpHistoryDao dkpHistoryDao;
    @Autowired private GuildDao guildDao;
    @Autowired private PlayerDao playerDao;
    @Autowired private PlayerService playerService;

    public Guild findById(Integer id) {
        return guildDao.find(id);
    }

    public List<Guild> list() {
        return guildDao.list();
    }

    public Guild findByUri(String guildUri) {
        return guildDao.findByUri(guildUri);
    }

    public void edit(Guild guild, EditGuildForm editGuildForm) {
        guild.setName(editGuildForm.getName());
        guild.setUri(editGuildForm.getUri());
        guildDao.update(guild);
    }

    @Transactional
    public void awardDkp(Guild guild, AwardDkpForm awardDkpForm) {
        logger.debug("Awarding DKP");
        List<Player> players = playerService.findByGuildId(guild.getId());
        logger.debug("Finished listing players.");

        // todo: Make this more efficient
        // - Batch DKP history inserts
        // - Write a single update for player DKP summary column.
        for(Player player : players) {
            logger.debug("Adding DKP history row.");
            // Add DKP history row
            DkpHistory dkpHistory = new DkpHistory();
            dkpHistory.setPlayer(player);
            dkpHistory.setDkp(awardDkpForm.getAmount());
            dkpHistoryDao.add(dkpHistory);
            logger.debug("Added DKP history row.");

            // Update summary column
            player.setDkp(player.getDkp() + awardDkpForm.getAmount());
            playerDao.update(player);
            logger.debug("Updated player summary column.");
        }
    }
}
