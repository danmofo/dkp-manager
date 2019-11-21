package com.dmoffat.dkpmanager;


import com.dmoffat.dkpmanager.service.DkpDecayIntervalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private static final Logger logger = LogManager.getLogger(ScheduledTasks.class);

    @Autowired private DkpDecayIntervalService dkpDecayIntervalService;

    @Scheduled(cron = "0 */3 * * * *")
    public void applyDkpDecay() {
        logger.debug("Running scheduled task: applyDkpDecay()");
//        dkpDecayIntervalService.processDkpDecay();
    }

}
