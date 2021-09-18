package com.yadav_anjalii.my_notes.util;

import androidx.room.TypeConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static com.yadav_anjalii.my_notes.util.Constants.DATE_TIME_FORMAT;

public class TimestampConverter {



    @TypeConverter
    public static Date fromTimestamp(String value) {
        if (value != null) {

            TimeZone timeZone = TimeZone.getDefault();
            DATE_TIME_FORMAT.setTimeZone(timeZone);
            try {
                return DATE_TIME_FORMAT.parse(value);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        } else return null;
    }

    @TypeConverter
    public static String dateToTimestamp(Date value) {
        TimeZone timeZone = TimeZone.getDefault();
        DATE_TIME_FORMAT.setTimeZone(timeZone);
        return  value == null ? null : DATE_TIME_FORMAT.format(value);

    }
}
