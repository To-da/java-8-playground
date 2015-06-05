package com.toda.java8.datetime.chrono;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.chrono.JapaneseDate;
import java.time.chrono.ThaiBuddhistDate;

/**
 * Mainly different calendar systems
 */
public class ChronoExample {

    public static void main(String[] args) {
        showDates();
    }

    private static void showDates() {
        ChronoLocalDate japaneseDate = JapaneseDate.now();
        System.out.println("Japanese date: " + japaneseDate + " Era: " + japaneseDate.getEra() + " Chronology: " + japaneseDate.getChronology());

        ChronoLocalDate thaiBuddhistDate = ThaiBuddhistDate.now();
        System.out.println("Thai date: " + thaiBuddhistDate + " Era: " + thaiBuddhistDate.getEra() + " Chronology: " + thaiBuddhistDate.getChronology());

        ChronoLocalDate localDate = LocalDate.now();
        System.out.println("ISO date: " + localDate + " Era: " + localDate.getEra() + " Chronology: " + localDate.getChronology());

        System.out.println(japaneseDate.isEqual(localDate));
        System.out.println(thaiBuddhistDate.isEqual(localDate));

        System.out.println("All supported chronologies:");
        for (Chronology chronology : Chronology.getAvailableChronologies()) {
            ChronoLocalDate date = chronology.dateNow();
            System.out.printf("   %20s: %s%n", chronology.getId(), date.toString());
        }
    }

}
