package com.hs3.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_DATE = "yyyy-MM-dd";
    public static final String FORMAT_TIME = "HH:mm:ss";

    public static Date addDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(5, n);
        return cal.getTime();
    }

    public static Date addHour(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(11, n);
        return cal.getTime();
    }

    public static Date addMinute(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(12, n);
        return cal.getTime();
    }

    public static Date AddSecond(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(13, n);
        return cal.getTime();
    }

    public static String timeFormat(int time) {
        Calendar cal = Calendar.getInstance();
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        cal.add(13, time);

        String str = format(cal.getTime(), "HH:mm:ss");

        int day = time / 86400;
        if (day > 0) {
            Integer h = Integer.valueOf(Integer.parseInt(str.substring(0, 2)) + day * 24);
            str = h + str.substring(2);
        }
        return str;
    }

    public static Timestamp toTimestamp(String c)
            throws ParseException {
        Date d = toDate(c);
        return new Timestamp(d.getTime());
    }

    public static boolean validateNormalFormat(String strDate) {
        return validateNormalFormat(strDate, "yyyy-MM-dd HH:mm:ss");
    }

    public static boolean validateNormalFormat(String strDate, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        try {
            format.parse(strDate);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static String format(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatDate(Date date) {
        return format(date, "yyyy-MM-dd");
    }

    public static String format(Date date, String format) {
        DateFormat fm = new SimpleDateFormat(format);
        return fm.format(date);
    }

    public static Date toDateNull(String c) {
        try {
            return toDate(c);
        } catch (Exception localException) {
        }
        return null;
    }

    public static Date toDateNull(String c, String format) {
        try {
            return toDate(c, format);
        } catch (Exception localException) {
        }
        return null;
    }

    public static Date toDate(String c)
            throws ParseException {
        return toDate(c, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date toDate(String c, String format)
            throws ParseException {
        DateFormat fm = new SimpleDateFormat(format);
        return fm.parse(c);
    }

    public static Date getToDay(int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        cal.add(5, day);
        return cal.getTime();
    }

    public static Date getToDay() {
        return getToDay(0);
    }

    public static Date getDate(Date d) {
        String date = formatDate(d);
        Date rel = toDateNull(date, "yyyy-MM-dd");
        return rel;
    }

    public static SimpleDateFormat returnSimpleDateFormat() {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return myFormatter;
    }

    public static String getTwotime(String sj1, String sj2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long time = 0L;
        try {
            Date date = myFormatter.parse(sj1);
            Date mydate = myFormatter.parse(sj2);
            time = (mydate.getTime() - date.getTime()) / 1000L;
        } catch (Exception e) {
            return "";
        }

        //jd-gui
        //return time;
        return (new StringBuilder(String.valueOf(time))).toString();
    }

    public static long getSecondBetween(Date minDate, Date maxDate) {
        long ween = maxDate.getTime() - minDate.getTime();
        ween /= 1000L;
        return ween;
    }

    public static long getTwoDay(Date d1, Date d2) {
        return getTwoDay(format(d1), format(d2));
    }

    public static long getTwoDay(String sj1, String sj2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0L;
        try {
            Date date = myFormatter.parse(sj1);
            Date mydate = myFormatter.parse(sj2);
            day = (mydate.getTime() - date.getTime()) / 86400000L;
        } catch (Exception e) {
            return 0L;
        }
        return day;
    }

    public static String getPreTime(String sj1, int jj, int sign) {
        /**jd-gui
         * SimpleDateFormat format;
         SimpleDateFormat format;
         if (sign == 0)
         {
         format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         }
         else
         {
         SimpleDateFormat format;
         if (sign == 1) {
         format = new SimpleDateFormat("yyyy-MM-dd");
         } else {
         format = new SimpleDateFormat("HH:mm:ss");
         }
         }
         String mydate1 = "";
         try
         {
         Date date1 = format.parse(sj1);
         long Time = date1.getTime() / 1000L + jj;
         date1.setTime(Time * 1000L);
         mydate1 = format.format(date1);
         }
         catch (Exception localException) {}
         return mydate1;*/

        SimpleDateFormat format;
        if (sign == 0)
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        else if (sign == 1)
            format = new SimpleDateFormat("yyyy-MM-dd");
        else
            format = new SimpleDateFormat("HH:mm:ss");
        String mydate1 = "";
        try {
            Date date1 = format.parse(sj1);
            long Time = date1.getTime() / 1000L + (long) jj;
            date1.setTime(Time * 1000L);
            mydate1 = format.format(date1);
        } catch (Exception exception) {
        }
        return mydate1;

    }

    public static int getWeekOfDate(Date dt) {
        Calendar cal = Calendar.getInstance();


        cal.setTime(dt);
        int w = cal.get(7) - 1;
        if (w == 0) {
            w = 7;
        }
        return w;
    }

    public static Date getDayByOne(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(5);
        if (day <= 15) {
            cal.set(5, 1);
        } else {
            cal.set(5, 16);
        }
        int a = n / 2;
        int b = n % 2;
        if (a > 0) {
            cal.add(2, a);
        }
        int day1 = cal.get(5);
        if (b > 0) {
            if (day1 == 1) {
                cal.set(5, 16);
            } else {
                cal.add(2, b);
                cal.set(5, 1);
            }
        }
        return cal.getTime();
    }

    public static Date getDayBySecond(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(5, 1);
        cal.add(2, n);
        return cal.getTime();
    }

    public static Date getStartDateByOne(Date curDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
        int day = cal.get(5);
        if (day >= 16) {
            cal.set(5, 1);
        } else {
            cal.add(2, -1);
            cal.set(5, 16);
        }
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        return cal.getTime();
    }

    public static Date getStartDateBySecond(Date curDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
        cal.add(5, -1);
        cal.set(5, 1);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        return cal.getTime();
    }

    public static Date getEndDateByContract(Date curDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
        cal.add(5, -1);
        cal.set(11, 23);
        cal.set(12, 59);
        cal.set(13, 59);
        return cal.getTime();
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 != null && cal2 != null) {
            return cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(6) == cal2.get(6);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    public static int getDayNum(Date curDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
        return cal.get(5);
    }
}
