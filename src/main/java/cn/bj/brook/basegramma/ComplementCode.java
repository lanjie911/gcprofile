package cn.bj.brook.basegramma;

import java.util.TreeMap;

public class ComplementCode {
    public static void main(String[] args) {
        int i = -1;
        System.out.println(Integer.toBinaryString(i));

        // 负数在内存中是补码表示，所以这个值是-1
        int m = 0b11111111111111111111111111111111;
        System.out.println(m);


        for(i=Integer.MIN_VALUE;i<0;i++){
            System.out.println(Integer.toBinaryString(i));
        }

        TreeMap<String, String> map = null;
    }
}
