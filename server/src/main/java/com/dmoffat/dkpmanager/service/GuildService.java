package com.dmoffat.dkpmanager.service;

import com.dmoffat.dkpmanager.dao.DkpDecayIntervalDao;
import com.dmoffat.dkpmanager.dao.DkpHistoryDao;
import com.dmoffat.dkpmanager.dao.GuildDao;
import com.dmoffat.dkpmanager.dao.PlayerDao;
import com.dmoffat.dkpmanager.model.*;
import com.dmoffat.dkpmanager.model.forms.AddDkpDecayIntervalForm;
import com.dmoffat.dkpmanager.model.forms.AwardDkpForm;
import com.dmoffat.dkpmanager.model.forms.DecayDkpForm;
import com.dmoffat.dkpmanager.model.forms.EditGuildForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
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
        dkpHistoryDao.add(dkpHistory);

        player.setDkp(player.getDkp() - decayDkpForm.getAmount());
        playerDao.update(player);

        return player.getDkp();
    }

    public void addDecayDkpInterval(Guild guildToAddTo, AddDkpDecayIntervalForm form) {
        DkpDecayInterval dkpDecayInterval = new DkpDecayInterval();
        dkpDecayInterval.setGuildId(guildToAddTo.getId());
        dkpDecayInterval.setUnitName(form.getUnitName());
        dkpDecayInterval.setUnitValue(form.getUnitValue());
        dkpDecayInterval.setDkp(form.getDkp());
        dkpDecayInterval.setNextOccurrence(getNextDkpDecayDate(form.getUnitName(), form.getUnitValue()));
        dkpDecayIntervalDao.add(dkpDecayInterval);
    }

    private LocalDate getNextDkpDecayDate(UnitName unitName, Integer unitValue) {
        LocalDate nextOccurrence = LocalDate.now();
        switch(unitName) {
            case DAYS:
                nextOccurrence = nextOccurrence.plusDays(unitValue);
                break;
            case WEEKS:
                nextOccurrence = nextOccurrence.plusWeeks(unitValue);
                break;
            case MONTHS:
                nextOccurrence = nextOccurrence.plusMonths(unitValue);
                break;
            case YEARS:
                nextOccurrence = nextOccurrence.plusYears(unitValue);
                break;
        }
        return nextOccurrence;
    }

    public void deleteDkpDecayInterval(Guild guildToDeleteFrom) {
        DkpDecayInterval interval = dkpDecayIntervalDao.find(guildToDeleteFrom.getId());
        dkpDecayIntervalDao.remove(interval);
    }
}
