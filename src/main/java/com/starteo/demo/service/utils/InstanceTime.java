package com.starteo.demo.service.utils;

import org.springframework.stereotype.Component;

import java.time.*;
import java.time.temporal.TemporalAdjusters;

@Component
public class InstanceTime {
    public static Instant now() {
        return LocalDateTime.now().atZone(ZoneId.of("UTC+3")).toInstant();
    }

    public static Instant getCurrentMondayOfTheWeek() {
        return LocalDate.now()
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
                .atStartOfDay()
                .atZone(ZoneId.of("UTC+3"))
                .toInstant();
    }

    public static Instant getCurrentSundayOfTheWeek() {
        return LocalDate.now()
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
                .atStartOfDay()
                .atZone(ZoneId.of("UTC+3"))
                .toInstant();
    }
}
