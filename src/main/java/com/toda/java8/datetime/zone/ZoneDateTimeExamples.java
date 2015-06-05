package com.toda.java8.datetime.zone;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class ZoneDateTimeExamples {

    public static void main(String[] args) {
        getZoneDateTimeExample();

        getOffsetDateTime();
    }

    private static void getZoneDateTimeExample() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        System.out.println(zonedDateTime);
        System.out.println(zonedDateTime.getOffset());
    }

    private static void getOffsetDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime, ZoneOffset.ofHours(-2));
        System.out.println(offsetDateTime);
    }


}
