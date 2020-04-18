package cn.bj.brook.algorithm.bitoper;

import java.text.SimpleDateFormat;

public class SimplePlus {
    public int getSum(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;
        int lower;
        int carrier;
        while (true) {
            lower = a ^ b;    // 计算低位
            System.out.println("数1="+Integer.toBinaryString(a));
            System.out.println("数2="+Integer.toBinaryString(b));
            System.out.println("低位(数1 异或 数2)="+Integer.toBinaryString(lower));
            carrier = a & b;  // 计算进位
            System.out.println("进位(数1 与 数2)="+Integer.toBinaryString(carrier));
            if (carrier == 0) break;
            a = lower;
            b = carrier << 1;
            System.out.println("进位左移="+Integer.toBinaryString(b));
            System.out.println("-----------------------------------------------");
        }
        return lower;
    }

    public static void main(String[] args) {
        SimplePlus plus = new SimplePlus();
        int t = plus.getSum(-15,7);
        System.out.println(t);
    }
}
