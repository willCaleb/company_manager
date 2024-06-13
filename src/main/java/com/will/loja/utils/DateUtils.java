package com.will.loja.utils;


import com.will.loja.enums.DatePattern;
import com.will.loja.enums.EnumDateFormat;
import com.will.loja.exception.CustomException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.function.Supplier;

public class DateUtils {

    public static final String TIMEZONE = "America/Sao_Paulo";

    public static final ZoneId ZONE_ID = ZoneId.of(TIMEZONE);

    public static String convertFromString(String date, DatePattern pattern) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern.getValue());
        Date parse = dateFormat.parse(date);
        return dateFormat.format(parse);
    }

    public static Date getDate() {
        return Calendar.getInstance().getTime();
    }

    public String getStringDate(DatePattern pattern) {
        return getFormatted(getDate(), pattern);
    }

    public static Supplier<Date> dateFromSupplier = Calendar.getInstance()::getTime;

    public static String getFormatted(Date date, DatePattern pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern.getValue());
        return dateFormat.format(date);
    }

    public static Date add(Integer amount, Integer field) {
        return add(null, amount, field);
    }

    public static Date add(Date date, Integer amount, Integer field) {

        if (Utils.isEmpty(date)) {
            date = getDate();
        }
        Calendar calendar = getCalendar(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    public static Calendar getCalendar() {
        return Calendar.getInstance(new Locale("pt", "BR"));
    }

    public static Calendar getCalendar(Date date) {
        Calendar calendar = getCalendar();
        calendar.setTime(date);
        return calendar;
    }

    public static Date subtract(Date date, Integer amount, Integer field) {
        return add(date, -amount, field);
    }

    public static LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZONE_ID).toLocalDate();
    }

    public static Integer dayOfWeek(Date date) {
        return getCalendar(date).get(Calendar.DAY_OF_WEEK) - 1;
    }

    public static Date parse(Date date, EnumDateFormat dateFormat) {
        try {
            if (Utils.isEmpty(dateFormat)) {
                dateFormat = EnumDateFormat.YYYYMMDDTHHMMSS;
            }

            String format = dateFormat.format(date);

            return dateFormat.parse(format);
        } catch (ParseException e) {
            throw new CustomException(e);
        }
    }

    public static boolean isToday(Date date) {
        return LocalDate.now().isEqual(toLocalDate(date));
    }
}





























