package com.dmoffat.dkpmanager.dao;

import com.dmoffat.dkpmanager.model.Guild;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class GuildDao extends HibernateDao<Guild, Integer> {
    public Guild findByUri(String guildUri) {
        Query query = entityManager().createQuery(
                "from Guild g where g.uri = :guildUri");
        query.setParameter("guildUri", guildUri);
        return getSingleResult(query);
    }
}
