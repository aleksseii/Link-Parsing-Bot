package ru.tinkoff.edu.java.scrapper.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Slf4j
@Component
public final class LinkUpdateScheduler {

    @Scheduled(fixedDelayString = "#{@schedulerIntervalMillis}")
    public void update() {
        log.info("Updating at " + Instant.now());
    }
}
