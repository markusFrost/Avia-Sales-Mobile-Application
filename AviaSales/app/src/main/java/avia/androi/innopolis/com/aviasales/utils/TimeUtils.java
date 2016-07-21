package avia.androi.innopolis.com.aviasales.utils;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

    public static String convertMillsToString (long mills){

        Date date = new Date(mills);
        Format format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return format.format(date);
    }

    public static String convertMillsToStringDate (long mills){

        Date date = new Date(mills);
        Format format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(date);
    }

    public static long convertStringToMills (String value){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date parsedDate = dateFormat.parse(value);
            long time = parsedDate.getTime();

            Calendar calendar = Calendar.getInstance();

            calendar.setTimeInMillis(time);

            calendar.set(Calendar.HOUR_OF_DAY, 10);
            calendar.set(Calendar.MINUTE, 30);
            calendar.set(Calendar.SECOND, 20);

            return calendar.getTimeInMillis();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
