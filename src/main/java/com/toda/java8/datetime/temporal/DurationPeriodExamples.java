package com.toda.java8.datetime.temporal;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.stream.IntStream;

public class DurationPeriodExamples {

    public static void main(String[] args) {
        periodExample();

        durationExample();

        daylightSavingExample();

        leapYearExample();
    }

    private static void durationExample() {
        final Duration duration = Duration.parse("PT1M");
        System.out.println("Duration: " + duration);

        final Duration between = Duration.between(LocalDateTime.now(), LocalDateTime.now().plusSeconds(2));
//        final Duration between = Duration.between(LocalDate.now(), LocalDate.now());
        System.out.println("Duration between: " + between);

        Duration durationHalfDay = Duration.between(LocalTime.MIDNIGHT,LocalTime.NOON);
        System.out.println("Duration half-day: " + durationHalfDay.toHours());
    }

    private static void periodExample() {
        final Temporal nowPlus2Days = Period.ofDays(2).addTo(Instant.now());
        System.out.println("Period: " + nowPlus2Days);

        final Period between = Period.between(LocalDate.now().minusWeeks(1), LocalDate.now().plus(3, ChronoUnit.CENTURIES));
        System.out.println("Period between: " +between);
    }

    /**
     * incorrect result when Duration is used
     */
    private static void daylightSavingExample() {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDate.of(2013, 10, 27), LocalTime.of(2, 30), ZoneId.of("Europe/Paris"));
        System.out.println(zonedDateTime);
        System.out.println(zonedDateTime.plus(Duration.ofDays(7)));
        System.out.println(zonedDateTime.plus(Period.ofDays(7)));
    }

    /**
     * incorrect result when Duration is used on leap year
     * @see java.time.chrono.IsoChronology#isLeapYear(long)
     * @see LocalDate#resolvePreviousValid(int, int, int)
     */
    private static void leapYearExample() {
        System.out.println(Year.of(2004).isLeap());
        System.out.println(Year.of(2004).length());
        LocalDateTime date = LocalDate.of(2003, 9, 13).atStartOfDay();
        System.out.println(date);

        System.out.println("-leap years:" + getNumberOfLeapYears(Year.from(date), Year.from(date).plusYears(1)));
        System.out.println(date.plus(Duration.ofDays(365)));
        System.out.println(date.plus(Period.ofDays(365)));    //one leap year - one day wrong
        System.out.println(date.plus(Period.ofYears(1)));
        System.out.println(date.plusYears(1));

        System.out.println("-leap years:" + getNumberOfLeapYears(Year.from(date), Year.from(date).plusYears(9)));
        System.out.println(date.plus(Duration.ofDays(365 * 9)));
        System.out.println(date.plus(Period.ofDays(365 * 9))); //two leap years - (2004, 2008)
        System.out.println(date.plus(Period.ofYears(1 * 9)));
        System.out.println(date.plusYears(1 * 9));

        System.out.println("-leap years:" + getNumberOfLeapYears(Year.from(date), Year.from(date).plusYears(13)));
        System.out.println(date.plus(Duration.ofDays(365 * 13)));
        System.out.println(date.plus(Period.ofDays(365 * 13))); //two leap years - (2004, 2008, 2012)
        System.out.println(date.plus(Period.ofYears(1 * 13)));
        System.out.println(date.plusYears(1 * 13));
    }
    
    static long getNumberOfLeapYears(Year from, Year to) {
        System.out.println("Counting leap years between " + from + " and " + to);
        return IntStream.rangeClosed(from.getValue(), to.getValue()).filter(i -> Year.of(i).isLeap()).count();
    }


}
