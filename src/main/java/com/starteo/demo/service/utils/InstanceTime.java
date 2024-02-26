package com.starteo.demo.service.utils;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import org.springframework.stereotype.Component;

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
