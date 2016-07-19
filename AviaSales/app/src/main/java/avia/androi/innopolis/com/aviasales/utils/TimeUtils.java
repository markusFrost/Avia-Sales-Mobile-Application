package avia.androi.innopolis.com.aviasales.utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    public static String convertMillsToString (long mills){

        Date date = new Date(mills);
        Format format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return format.format(date);
    }
}
