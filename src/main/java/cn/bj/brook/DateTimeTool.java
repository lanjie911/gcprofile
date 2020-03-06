package cn.bj.brook;

import java.text.DateFormat;
import java.util.Date;

public class DateTimeTool {
    public static String printCurrentTime(){
        return DateFormat.getDateTimeInstance().format(new Date());
    }

    public static void main(String[] args) {
        System.out.println(DateTimeTool.printCurrentTime());
    }
}
