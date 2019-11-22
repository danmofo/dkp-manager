package com.dmoffat.dkpmanager.service;

import com.dmoffat.dkpmanager.dao.DkpDecayIntervalDao;
import com.dmoffat.dkpmanager.dao.DkpHistoryDao;
import com.dmoffat.dkpmanager.dao.GuildDao;
import com.dmoffat.dkpmanager.model.*;
import com.dmoffat.dkpmanager.model.forms.AddDkpDecayIntervalForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Daniel Moffat
 */
@Service
public class DkpDecayIntervalService {
    private static final Logger logger = LogManager.getLogger(DkpDecayIntervalService.class);

    @Autowired private DkpDecayIntervalDao dkpDecayIntervalDao;
    @Autowired private DkpHistoryDao dkpHistoryDao;
    @Autowired private GuildDao guildDao;

    @Transactional
    public void processDkpDecay() {
        logger.debug("processDkpDecay()");

        // Find the decays that should be applied today.
        List<DkpDecayInterval> decays = dkpDecayIntervalDao.findDueDecays(LocalDate.now());
        logger.debug("Found " + decays.size() + " decays that need to be applied.");

        // Reduce each players DKP in the guild by the specified amount - could've been done in the loop below, but it's
        // more efficient to do these with one query..
        dkpDecayIntervalDao.applyToPlayers(LocalDate.now());

        // Apply the decays to each player in the guild
        logger.debug("Applying decays to each guild..");
        for(DkpDecayInterval decay : decays) {
            // todo: write a general purpose findWithPlayers or similar method and let find just return the guild data.
            Guild guild = guildDao.find(decay.getGuildId());
            logger.debug("Applying decay to '" + guild.getName() + "'");

            for(Player player : guild.getPlayers()) {
                logger.debug("Decaying '" + player.getName() + "'");
                // todo: Make this more efficient by using batch inserts
                DkpHistory dkpHistory = new DkpHistory();
                dkpHistory.setPlayer(player);
                dkpHistory.setDkp(-decay.getDkp());
                dkpHistory.setReason("Automated decay.");
                dkpHistoryDao.add(dkpHistory);
            }

            // Set the next occurrence after this.
            LocalDate nextOccurrence = getNextDkpDecayDate(decay);
            logger.debug("Changed next occurrence from '" + decay.getNextOccurrence() + "' to '" + nextOccurrence + "'");
            decay.setNextOccurrence(nextOccurrence);
            dkpDecayIntervalDao.update(decay);
        }
    }

    public void addDecayDkpInterval(Guild guildToAddTo, AddDkpDecayIntervalForm form) {
        DkpDecayInterval dkpDecayInterval = new DkpDecayInterval();
        dkpDecayInterval.setGuildId(guildToAddTo.getId());
        dkpDecayInterval.setUnitName(form.getUnitName());
        dkpDecayInterval.setUnitValue(form.getUnitValue());
        dkpDecayInterval.setDkp(form.getDkp());

        // Set the start date to the value they entered in the form, or if nothing was entered, start from the interval
        // they specified.
        if(form.getStartDate() == null) {
            dkpDecayInterval.setNextOccurrence(getNextDkpDecayDate(form.getUnitName(), form.getUnitValue()));
        } else {
            dkpDecayInterval.setNextOccurrence(form.getStartDate());
        }

        dkpDecayIntervalDao.add(dkpDecayInterval);
    }

    private LocalDate getNextDkpDecayDate(DkpDecayInterval dkpDecayInterval) {
        return getNextDkpDecayDate(dkpDecayInterval.getUnitName(), dkpDecayInterval.getUnitValue());
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
