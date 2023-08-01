package com.hieplp.recipe.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    public static LocalDate today() {
        return LocalDate.now();
    }

    public static Date getCurrentDate() {
        return new Date();
    }

    public static Date addSeconds(Long date, int seconds) {
        var calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        calendar.add(Calendar.SECOND, seconds);
        return calendar.getTime();
    }

    public static Date addSeconds(Date date, int seconds) {
        return addSeconds(date.getTime(), seconds);
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
