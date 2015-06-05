package com.toda.java8.datetime.time;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class BasicDateTimeExamples {

    public static void main(String[] args) {
        printInstant();

        printLocalDate();

        printLocalTime();

        printLocalDateTime();

        printClockExample();
    }

    private static void printLocalDateTime() {
        LocalDate localDate = LocalDate.now();
        LocalTime localTimeNow = LocalTime.now();
        LocalDateTime localDateTime = localDate.atTime(localTimeNow);
        System.out.println("LocalDateTimeNow: " + localDate.atTime(12, 00));

    }

    private static void printLocalTime() {
        LocalTime localTimeNow = LocalTime.now();
        System.out.println("LocalTimeNow: " + localTimeNow.getHour());

        LocalTime localTimeMax = LocalTime.MAX;
        System.out.println("LocalTimeMax: " + localTimeMax);
    }

    private static void printLocalDate() {
        final LocalDate localDateNow = LocalDate.now();
        System.out.println("LocalDateNow: " + localDateNow);

        final LocalDate localDateTwoDaysAfterEpoch = LocalDate.ofEpochDay(2);
        System.out.println("LocalDateTwoDaysAfterEpoch: " + localDateTwoDaysAfterEpoch);

//        final LocalDate localDateNonExisting = LocalDate.of(2012, 2, 31);
        final LocalDate localDateNonExisting = LocalDate.of(2012, 1, 31).plusMonths(1);
        System.out.println("LocalDateNonExisting: " + localDateNonExisting);
    }

    private static void printInstant() {
        final Instant instantNegative = Instant.EPOCH.minusSeconds(200);
        System.out.println("InstantNegative: " + instantNegative + " | " + instantNegative.getEpochSecond());

        final Instant instantEpoch = Instant.EPOCH;
        System.out.println("InstantEpoch: " + instantEpoch + " | " + instantEpoch.getEpochSecond());

        final Instant instantNow = Instant.now();
        System.out.println("InstantNow: " + instantNow + " | " + instantNow.getEpochSecond());

//        Year year = Year.from(Instant.now()));   //fails in runtime use: Year.now() instead

//        System.out.println(instantNow.isSupported(ChronoField.MONTH_OF_YEAR));
//       int months = instantNow.get(ChronoField.MONTH_OF_YEAR); //throws exception

        int nanosOfSecond = instantNow.get(ChronoField.NANO_OF_SECOND);
//        int seconds = instantNow.get(ChronoField.INSTANT_SECONDS);
        long seconds = instantNow.getLong(ChronoField.INSTANT_SECONDS);
        System.out.println("Instant nanos:" + nanosOfSecond+  " Secs:" +seconds);
    }

    private static void printClockExample() {
        final Shop shopInstance = new Shop(Clock.systemDefaultZone());
        final boolean isOpenNow = shopInstance.isOpen();
        final ZonedDateTime nextOpenDateTime = shopInstance.nextOpenDateTime();
        System.out.println("Is shop opened now : " + isOpenNow + " but if will be on " + nextOpenDateTime.format(DateTimeFormatter.RFC_1123_DATE_TIME/**DateTimeFormatter.ofPattern("dd.MMM uuuu / HH:mm:ss")**/));

    }

    static class Shop {

        private final Clock clock;

        Shop(Clock clock) {
            this.clock = clock;
        }

        public boolean isOpen() {
            ZonedDateTime now = ZonedDateTime.now(clock);
            if (isWeekend(now))
                return false;

            LocalTime time = now.toLocalTime();
            return time.isAfter(LocalTime.of(7, 30)) && time.isBefore(LocalTime.of(18, 0));
        }

        public ZonedDateTime nextOpenDateTime() {
            return ZonedDateTime.now(clock).with(new OpenDateAdjuster()).with(new OpenTimeAdjuster());
        }

        private static boolean isWeekend(ZonedDateTime dateTime) {
            return dateTime.getDayOfWeek() == DayOfWeek.SATURDAY || dateTime.getDayOfWeek() == DayOfWeek.SUNDAY;
        }

        static class OpenDateAdjuster implements TemporalAdjuster {
            @Override
            public Temporal adjustInto(Temporal temporal) {

                ZonedDateTime dateTimeToBeAdjusted = ZonedDateTime.from(temporal);

                if (isWeekend(dateTimeToBeAdjusted)) {
                    return dateTimeToBeAdjusted.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
                } else return temporal;
            }
        }

        static class OpenTimeAdjuster implements TemporalAdjuster {
            @Override
            public Temporal adjustInto(Temporal temporal) {

                ZonedDateTime dateTimeToBeAdjusted = ZonedDateTime.from(temporal);
                LocalTime timeToBeAdjusted = LocalTime.from(temporal);

                if (timeToBeAdjusted.isBefore(LocalTime.of(7, 30))) {
                    return dateTimeToBeAdjusted.withHour(7).withMinute(30).withSecond(0).withNano(0);
                } else if (timeToBeAdjusted.isAfter(LocalTime.of(18, 00))) {
                    if (dateTimeToBeAdjusted.plusDays(1).getDayOfWeek() == DayOfWeek.SATURDAY) {
                        return dateTimeToBeAdjusted.with(TemporalAdjusters.next(DayOfWeek.MONDAY)).withHour(7).withMinute(30).withSecond(0).withNano(0);
                    } else {
                        return dateTimeToBeAdjusted.plusDays(1).withHour(7).withMinute(30).withSecond(0).withNano(0);
                    }
                } else {
                    return temporal;
                }
            }
        }
    }

}
