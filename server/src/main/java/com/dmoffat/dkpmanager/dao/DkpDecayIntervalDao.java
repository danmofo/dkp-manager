package com.dmoffat.dkpmanager.dao;

import com.dmoffat.dkpmanager.model.DkpDecayInterval;
import com.dmoffat.dkpmanager.model.Guild;
import org.springframework.stereotype.Repository;

@Repository
public class DkpDecayIntervalDao extends HibernateDao<DkpDecayInterval, Guild> {
}
