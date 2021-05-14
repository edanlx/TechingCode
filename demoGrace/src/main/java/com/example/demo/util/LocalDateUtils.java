package com.example.demo.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class LocalDateUtils {
    public static Date ldtTransferToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime DateTransferToldt(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static LocalDate getLocaleDate(String pattern, String dateStr) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(dateStr, dtf);
    }

    public static LocalDateTime getLocaleDateTime(String pattern, String dateStr) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateStr, dtf);
    }

    public static LocalDate getCurrentWeek() {
        return LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    }

    public static void main(String[] args) {
        System.out.println(getLocaleDate("yyyy-MM-dd", "2020-01-01"));
        System.out.println(getLocaleDateTime("yyyy-MM-dd HH:mm:ss", "2020-01-01 10:10:10"));

    }
}
