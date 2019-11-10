package com.dmoffat.dkpmanager.service;

import com.dmoffat.dkpmanager.dao.PlayerDao;
import com.dmoffat.dkpmanager.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Autowired
    private PlayerDao playerDao;

    public Player findById(Integer playerId) {
        return playerDao.find(playerId);
    }

}
