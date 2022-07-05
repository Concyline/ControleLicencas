package br.com.controle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimestampUtils {

    @Deprecated
    public static String getISO8601StringForCurrentDate() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + 3);
        return df.format(calendar.getTime());
    }

    public static String getISOStringForCurrentDate() {
        DateFormat df = new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df.setTimeZone (TimeZone.getTimeZone("GMT+00:00"));
        return df.format(new Date());
    }

}
