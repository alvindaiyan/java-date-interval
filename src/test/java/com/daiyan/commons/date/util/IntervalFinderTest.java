package com.daiyan.commons.date.util;

import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by yan.dai on 12/10/2015.
 */
public class IntervalFinderTest
{
    @Test
    public void testGetDateIntervalByLength() throws Exception
    {
        IntervalFinder finder = IntervalFinder.get();

        List<IntervalFinder.DateInterval> results = finder.getDateIntervalByLength(finder.getDate("2015-10-05"), finder.getDate("2015-10-13"), 7);

        assertTrue(results != null);
        assertTrue(results.size() == 2);

        assertTrue(results.get(0).getStartDate().equals(finder.getDate("2015-10-05")));
        assertTrue(results.get(1).getEndDate().equals(finder.getDate("2015-10-13")));

        for (IntervalFinder.DateInterval inv : results) {
            System.out.println(inv.getStartDate() + " to " + inv.getEndDate());
        }
    }

    @Test
    public void testGetDateIntervalByLengthNullPointer() throws Exception
    {
        IntervalFinder finder = IntervalFinder.get();
        try {
            finder.getDateIntervalByLength(null, finder.getDate("2015-10-13"), 7);
        } catch (NullPointerException ex) {
            assertTrue(true);
        }
    }


    @Test
    public void testGetDateIntervalByLengthIllegalStateException() throws Exception
    {
        IntervalFinder finder = IntervalFinder.get();
        try {
            finder.getDateIntervalByLength(finder.getDate("2015-10-23"), finder.getDate("2015-10-13"), 7);
        } catch (IllegalStateException ex) {
            assertTrue(true);
        }

        try {
            finder.getDateIntervalByLength(finder.getDate("2015-10-1"), finder.getDate("2015-10-13"), 0);
        } catch (IllegalStateException ex) {
            assertTrue(true);
        }
    }
}