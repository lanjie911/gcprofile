package cn.bj.brook.basegramma.datetime;

import cn.bj.brook.interview.CanCompleteTrip;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import static java.lang.System.out;

public class DemoCalendar {
    public static void main(String[] args) {
        Calendar china = Calendar.getInstance(Locale.CHINA);
        china.setLenient(false);
        china.set(Calendar.HOUR_OF_DAY,23);
        china.set(Calendar.MINUTE,59);
        china.set(Calendar.SECOND,59);
        out.println(china.toString());

        china.add(Calendar.SECOND,-5);
        out.println(china.toString());
    }
}
