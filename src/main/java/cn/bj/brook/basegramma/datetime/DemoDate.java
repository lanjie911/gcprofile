package cn.bj.brook.basegramma.datetime;

import java.util.Date;

public class DemoDate {
    public static void main(String[] args) {
        long mills = System.currentTimeMillis();
        Date now = new Date(mills);
        // 5秒之后
        Date later = new Date(mills + 5000);
        System.out.println(now.before(later));
        Date earlier = new Date(mills - 5000);
        System.out.println(now.after(earlier));

        // 判断是否相同
        // 打印true
        Date copy = new Date(mills);
        System.out.println(now.equals(copy));

        // 比较
        // 打印-1 1 0
        System.out.println(now.compareTo(later));
        System.out.println(now.compareTo(earlier));
        System.out.println(now.compareTo(copy));

        System.out.println(now);
    }
}
