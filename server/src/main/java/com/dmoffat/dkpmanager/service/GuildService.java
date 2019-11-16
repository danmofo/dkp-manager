package com.dmoffat.dkpmanager.service;

import com.dmoffat.dkpmanager.dao.GuildDao;
import com.dmoffat.dkpmanager.model.Guild;
import com.dmoffat.dkpmanager.model.forms.EditGuildForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuildService {

    @Autowired
    private GuildDao guildDao;

    public Guild findById(Integer id) {
        return guildDao.find(id);
    }

    public List<Guild> list() {
        return guildDao.list();
    }

    public Guild findByUri(String guildUri) {
        return guildDao.findByUri(guildUri);
    }

    public void edit(Guild guild, EditGuildForm editGuildForm) {
        guild.setName(editGuildForm.getName());
        guild.setUri(editGuildForm.getUri());
        guildDao.update(guild);
    }
}
