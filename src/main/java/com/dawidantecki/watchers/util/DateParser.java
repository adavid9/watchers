package com.dawidantecki.watchers.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {

    private static Logger logger = LoggerFactory.getLogger(DateParser.class);

    public static Date parseDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = null;

        try {
            if ((date != null) && (!date.equals("")))
                parsed = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            logger.error("There was a problem with parsing " + date + " date. Error message = " + e.getMessage());
        }

        return parsed;
    }
}
