package com.dmoffat.dkpmanager.dao;

import com.dmoffat.dkpmanager.model.DkpDecayInterval;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Repository
public class DkpDecayIntervalDao extends HibernateDao<DkpDecayInterval, Integer> {

    // don't think we need this...
    public List<DkpDecayInterval> findDueDecays(LocalDate date) {
        Query query = entityManager().createQuery(
            "from DkpDecayInterval i " +
            "where i.nextOccurrence = :date");
        query.setParameter("date", date);

        return getResultList(query);
    }

    /**
     * Apply the given DKP decay interval, subtracting DKP from each player in the guild and adding a row to the DKP history
     * tables.
     */
    public void applyToPlayers(LocalDate date) {
        Query query = entityManager().createNativeQuery(
            "update player p " +
            "join guild g on g.id = p.guild_id " +
            "join dkp_decay_interval i on i.guild_id = g.id " +
            "set p.dkp = p.dkp - i.dkp " +
            "where i.next_occurrence = :date");
        query.setParameter("date", date);
        query.executeUpdate();
    }

}
