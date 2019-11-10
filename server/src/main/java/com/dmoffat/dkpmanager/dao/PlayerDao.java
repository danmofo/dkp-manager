package com.dmoffat.dkpmanager.dao;

import com.dmoffat.dkpmanager.model.Player;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerDao extends HibernateDao<Player, Integer> {
}
