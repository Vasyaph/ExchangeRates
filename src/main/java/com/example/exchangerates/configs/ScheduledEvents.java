package com.example.exchangerates.configs;

import com.example.exchangerates.services.DailyUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduledEvents {
    @Autowired
    DailyUpdateService updateService;

    @Scheduled(cron = "0 47 13 * * *")
    public void getApi() {
        updateService.update();
    }
}
