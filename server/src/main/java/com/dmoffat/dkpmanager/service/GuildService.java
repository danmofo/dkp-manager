package com.dmoffat.dkpmanager.service;

import com.dmoffat.dkpmanager.dao.DkpDecayIntervalDao;
import com.dmoffat.dkpmanager.dao.DkpHistoryDao;
import com.dmoffat.dkpmanager.dao.GuildDao;
import com.dmoffat.dkpmanager.dao.PlayerDao;
import com.dmoffat.dkpmanager.model.DkpHistory;
import com.dmoffat.dkpmanager.model.Guild;
import com.dmoffat.dkpmanager.model.Player;
import com.dmoffat.dkpmanager.model.forms.AwardDkpForm;
import com.dmoffat.dkpmanager.model.forms.DecayDkpForm;
import com.dmoffat.dkpmanager.model.forms.EditGuildForm;
import com.dmoffat.dkpmanager.model.pagination.Results;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GuildService {
    private static final Logger logger = LogManager.getLogger(GuildService.class);

    @Autowired private DkpDecayIntervalDao dkpDecayIntervalDao;
    @Autowired private DkpHistoryDao dkpHistoryDao;
    @Autowired private GuildDao guildDao;
    @Autowired private PlayerDao playerDao;
    @Autowired private PlayerService playerService;

    public Guild findById(Integer id) {
        return guildDao.find(id);
    }

    // todo: Replace with the method below when we get round to it.
    public List<Guild> list() {
        return guildDao.list();
    }

    public Results<Guild> list(int pageNum) {
        Results.Parameters params = new Results.Parameters();
        params.setPage(pageNum);

        Results<Guild> results = new Results<>(params);
        results.setItems(guildDao.list(params));
        results.setNumFound(guildDao.count());

        return results;
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
            dkpHistory.setReason(awardDkpForm.getReason());
            dkpHistory.setDkp(awardDkpForm.getAmount());
            dkpHistoryDao.add(dkpHistory);
            logger.debug("Added DKP history row.");

            // Update summary column
            player.setDkp(player.getDkp() + awardDkpForm.getAmount());
            playerDao.update(player);
            logger.debug("Updated player summary column.");
        }
    }

    /**
     * @return The players new DKP value.
     */
    @Transactional
    public Double decayDkp(Player editor, DecayDkpForm decayDkpForm) {
        Player player = playerService.findById(decayDkpForm.getPlayerId());

        if(!player.getGuild().getId().equals(editor.getGuild().getId())) {
            logger.warn("User tried to edit DKP for a player who doesn't belong to their guild.");
            return null;
        }

        DkpHistory dkpHistory = new DkpHistory();
        dkpHistory.setPlayer(player);
        dkpHistory.setDkp(-decayDkpForm.getAmount());
        dkpHistory.setReason(decayDkpForm.getReason());
        dkpHistoryDao.add(dkpHistory);

        player.setDkp(player.getDkp() - decayDkpForm.getAmount());
        playerDao.update(player);

        return player.getDkp();
    }

    public Guild findByInviteCode(String inviteCode) {
        return guildDao.findByInviteCode(inviteCode);
    }
}
