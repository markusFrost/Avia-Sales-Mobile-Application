package com.aviasales.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TImeUtils {
	
public static long getEndOfDay (long time){
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTimeInMillis(time);
		
		calendar.set(Calendar.HOUR_OF_DAY, 23);
	    calendar.set(Calendar.MINUTE, 59);
	    calendar.set(Calendar.SECOND, 59);
	    calendar.set(Calendar.MILLISECOND, 59);
	    
	    return calendar.getTimeInMillis();
		
	}
	
public static long getStartOfDay (long time){
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTimeInMillis(time);
		
		calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 1);
	    
	    return calendar.getTimeInMillis();
		
	}

public static long convertStringWithTimeToMills (String value){
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    try {
        Date parsedDate = dateFormat.parse(value);
        long time = parsedDate.getTime();

        return time;
    } catch (ParseException e) {
        e.printStackTrace();
    }
    return 0;
}

}
