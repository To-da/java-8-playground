package com.toda.java8.datetime.temporal;

import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class AdjusterExamples {

    public static void main(String[] args) {
        withAdjusters();

        withTruncation();
    }

    private static void withTruncation() {
        LocalDateTime localDateTime = LocalDateTime.of(2012, 2, 29, 16, 32);

        LocalTime truncatedTime = localDateTime.truncatedTo(ChronoUnit.HOURS).toLocalTime();

        System.out.println(localDateTime.truncatedTo(ChronoUnit.HOURS));
    }

    private static void withAdjusters() {
        LocalDateTime localDateTime = LocalDateTime.of(2012, 2, 29, 16, 32);
        System.out.println(localDateTime.minusYears(1));
        System.out.println(localDateTime.minusDays(365));

        final LocalDateTime withTemporalAdjuster = localDateTime.with(firstDayOfMonth());
        System.out.println(withTemporalAdjuster);

        final LocalDateTime withTemporalField = localDateTime.with(DAY_OF_MONTH, 1);
        System.out.println(withTemporalField);
    }


}
