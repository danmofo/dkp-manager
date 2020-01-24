package com.dmoffat.dkpmanager.dao;

import com.dmoffat.dkpmanager.model.DkpHistory;
import com.dmoffat.dkpmanager.model.Player;
import com.dmoffat.dkpmanager.model.pagination.Results;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class DkpHistoryDao extends HibernateDao<DkpHistory, Integer> {

    public List<DkpHistory> findByPlayerId(Integer playerId, Results.Parameters params) {
        Query query = entityManager().createQuery(
                "from DkpHistory h where h.player.id = :playerId");
        query.setParameter("playerId", playerId);
        query.setMaxResults(params.getItemsPerPage());
        query.setFirstResult(params.getOffset());
        return getResultList(query);
    }

    public Long countByPlayerId(Integer playerId) {
        Query query = entityManager().createQuery(
                "select count(*) from DkpHistory h where h.player.id = :playerId");
        query.setParameter("playerId", playerId);
        return (Long)query.getSingleResult();
    }

}
