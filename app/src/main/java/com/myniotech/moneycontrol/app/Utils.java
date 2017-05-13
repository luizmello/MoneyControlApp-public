package com.myniotech.moneycontrol.app;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by luiz on 24/04/17.
 */

public class Utils {

    public static String longToCurrency(Long value) {

        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.getDefault());
        return nf.format(value / 100.0);

    }

    public static String dateFormat(Calendar calendar) {

        final DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        return df.format(calendar.getTime());

    }

    public static String dateFormat(float f) {

        final DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        return df.format(f);

    }

    public static String dateFormat(Date date) {

        final DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        return df.format(date);

    }

    public static String dateFormat(LocalDateTime localDateTime) {


        DateTimeFormatter timeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy hh:mm");

        return timeFormatter.print(localDateTime);
    }

    public static int getHoursFromNowToDate(DateTime end) {

        DateTime startTime;

        startTime = DateTime.now();
        Period p = new Period(startTime, end);

        return p.getHours();

    }

    /**
     * Converts Unix time to Calendar instance.
     */
    public static Calendar unixToCalendar(long unixTime){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(unixTime);
        return calendar;
    }
}
