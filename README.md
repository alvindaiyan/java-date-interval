# Find Date Interval by Java

## Find by intervals by days

Find all the interval dates from 2015-10-05 to 2015-10-13 by every 7 days.

``` java
 public void main(String[] args) {
     IntervalFinder finder = IntervalFinder.get();
     List<IntervalFinder.DateInterval> results = finder.getDateIntervalByLength(finder.getDate("2015-10-05"), finder.getDate("2015-10-13"), 7);
     for (IntervalFinder.DateInterval inv : results) {
        System.out.println(inv.getStartDate() + " to " + inv.getEndDate());
     }
 }


```
The result will be:
```
Mon Oct 05 00:00:00 NZDT 2015 to Mon Oct 12 00:00:00 NZDT 2015
Mon Oct 12 00:00:00 NZDT 2015 to Tue Oct 13 00:00:00 NZDT 2015


```
