package cn.bj.brook.basegramma.datetime;

import java.text.*;
import java.util.Date;
import java.util.Locale;

import static java.lang.System.out;

public class DemoDataFormat {
    public static void main(String[] args) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("a yyyy-MM-dd E", Locale.CHINA);
        SimpleDateFormat sdf2 = new SimpleDateFormat("aaaa yyyy-MM-dd EEEE",Locale.CHINA);

        SimpleDateFormat sdf3 = new SimpleDateFormat("a yyyy-MM-dd E", Locale.US);
        SimpleDateFormat sdf4 = new SimpleDateFormat("aaaa yyyy-MM-dd EEEE",Locale.US);

        Date date = new Date(System.currentTimeMillis());
        out.println(sdf1.format(date));
        out.println(sdf2.format(date));
        out.println(sdf3.format(date));
        out.println(sdf4.format(date));
    }
}
