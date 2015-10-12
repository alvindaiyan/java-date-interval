package com.daiyan.commons.date.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yan.dai on 12/10/2015.
 */
public class IntervalFinder {

    private static IntervalFinder INSTANCE = null;
    public final static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    public static IntervalFinder get() {
        if(INSTANCE == null) {
            INSTANCE = dateFormat == null ? new IntervalFinder() : new IntervalFinder(dateFormat);
        }
        return INSTANCE;
    }

    public static IntervalFinder get(String dateformat) {
        INSTANCE = new IntervalFinder(dateformat);
        return INSTANCE;
    }

    private IntervalFinder() {
        this(DEFAULT_DATE_FORMAT);
    }


    private IntervalFinder(String dateFormatStr) {
        this(new SimpleDateFormat(dateFormatStr));
    }

    private IntervalFinder(DateFormat custDateFormat) {
        dateFormat = custDateFormat;
    }

    public Date getDate(String dateStr)
    {
        try
        {
            return dateFormat.parse(dateStr);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<DateInterval> getDateIntervalByLength(Date startDate, Date endDate, int daysBetween) {
        if(startDate == null) {
            throw new NullPointerException("start date cannot be null");
        }

        if(daysBetween <= 0) {
            throw new IllegalStateException("daysBetween has to be greater than 0");
        }

        endDate = endDate == null ? new Date() : endDate; // if no end date, just use current date as end date

        if(startDate.after(endDate)) {
            throw new IllegalStateException("start date is later than the end date");
        }

        Date current = startDate;
        System.out.println("Going to find all the date interval start " + current
                + " end to "  + endDate + " by every " + daysBetween + " days");

        List<DateInterval> result = new ArrayList<DateInterval>();



        Calendar calendar = new GregorianCalendar();
        calendar.setTime(current);

        do {
            calendar.add(Calendar.DATE, daysBetween);   // increment the dates by the daysBetween

            if(calendar.getTime().after(endDate) ) {
                System.out.println(dateFormat.format(current) + " to " + dateFormat.format(endDate.getTime()));
                result.add(new DateInterval(current, endDate));
                return result;
            } else {
                System.out.println(dateFormat.format(current) + " to " + dateFormat.format(calendar.getTime()));
                result.add(new DateInterval(current, calendar.getTime()));
            }

            current = calendar.getTime();
        } while (calendar.getTime().before(endDate));

        return result;
    }

    public class DateInterval {
        private Date startDate, endDate;

        public DateInterval(Date startDate, Date endDate)
        {
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public Date getStartDate()
        {
            return startDate;
        }

        public Date getEndDate()
        {
            return endDate;
        }
    }

    private static DateFormat dateFormat;

}
