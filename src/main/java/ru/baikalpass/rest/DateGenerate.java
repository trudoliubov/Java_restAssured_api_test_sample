package ru.baikalpass.rest;

import org.codehaus.groovy.transform.PackageScopeASTTransformation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DateGenerate {

    public static String genDate(String date) throws ParseException {
    
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_YEAR, Integer.parseInt(date));
        return date = dateFormat.format(calendar.getTime());
    }
    public static String genDateMillisecond(String date) throws ParseException {

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_YEAR, Integer.parseInt(date));
        date = dateFormat.format(calendar.getTime());
        Date dateMillisecond = dateFormat.parse(date);
        calendar.setTime(dateMillisecond);
        Long time = calendar.getTimeInMillis();
        String timeMillisecond = Long.toString(time);
        return timeMillisecond;

    }

   /* private static String genBeginDate(){
        return genBeginDate();
    }*/
}
/*Date parseBeginDateMillisecond =dateFormat.parse(beginDate);

        calendar.add(Calendar.DAY_OF_YEAR, 6);
        this.endDate = dateFormat.format(calendar.getTime());
        Date parseEndDateMillisecond =dateFormat.parse(endDate);

        calendar.setTime(parseBeginDateMillisecond);
        this.beginDateMillisecond = calendar.getTimeInMillis();

        calendar.setTime(parseEndDateMillisecond);
        this.endDateMillisecond = calendar.getTimeInMillis();*/