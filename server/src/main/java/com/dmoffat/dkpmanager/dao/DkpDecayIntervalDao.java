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
        // todo

        /**
         * select *
         * from dkp_decay_interval
         * where next_occurrence
         */
    }

}
