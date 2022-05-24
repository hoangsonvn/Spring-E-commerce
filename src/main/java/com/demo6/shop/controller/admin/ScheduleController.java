package com.demo6.shop.controller.admin;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleController {
    @Scheduled(cron = "*/10 * * * * *")
    public void scheduleFixedDelayTask() {
      System.out.println(
                "Fixed delay task - " + System.currentTimeMillis() / 1000);
    }
}
