package com.dmoffat.dkpmanager.dao;

import com.dmoffat.dkpmanager.model.Guild;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class GuildDao extends HibernateDao<Guild, Integer> {

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
}
