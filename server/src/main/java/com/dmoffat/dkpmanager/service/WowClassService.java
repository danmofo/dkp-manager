package com.dmoffat.dkpmanager.service;

import com.dmoffat.dkpmanager.dao.WowClassDao;
import com.dmoffat.dkpmanager.model.WowClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WowClassService {

    @Autowired
    private WowClassDao wowClassDao;

    public List<WowClass> list() {
        return wowClassDao.list();
    }

}
