package com.dmoffat.dkpmanager.dao;

import com.dmoffat.dkpmanager.model.Guild;
import com.dmoffat.dkpmanager.model.pagination.Results;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

// todo: Refactor the methods which find a guild by a property to use a generic method using the Criteria API (or entity manager equivalent)
@Repository
public class GuildDao extends HibernateDao<Guild, Integer> {

    public List<Guild> list(Results.Parameters params) {
        Query query = entityManager().createQuery("from Guild g");
        query.setMaxResults(params.getItemsPerPage());
        query.setFirstResult(params.getOffset());
        return getResultList(query);
    }

    @Override
    public Guild find(Integer primaryKey) {
        Query query = entityManager().createQuery(
                "from Guild g " +
                "left join fetch g.dkpDecayInterval " +
                "left join fetch g.players " +
                "where g.id = :guildId");
        query.setParameter("guildId", primaryKey);
        return getSingleResult(query);
    }

    public Guild findByUri(String guildUri) {
        Query query = entityManager().createQuery(
                "from Guild g " +
                "left join fetch g.players " +
                "where g.uri = :guildUri");
        query.setParameter("guildUri", guildUri);
        return getSingleResult(query);
    }

    public Guild findByInviteCode(String inviteCode) {
        Query query = entityManager().createQuery(
                "from Guild g " +
                "where g.inviteCode = :inviteCode");
        query.setParameter("inviteCode", inviteCode);
        return getSingleResult(query);
    }
}
