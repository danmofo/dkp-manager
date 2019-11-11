package com.dmoffat.dkpmanager.dao;

import com.dmoffat.dkpmanager.model.Player;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class PlayerDao extends HibernateDao<Player, Integer> {
    public Player findByEmail(String email) {
        Query query = entityManager().createQuery("from Player p where p.email = :email");
        query.setParameter("email", email);
        return getSingleResult(query);
    }
}
