package cn.bj.brook.algorithm.bitoper;

public class BitUtil {
    public static String perfect32IntBinaryString(int x){
        String t = Integer.toBinaryString(x);
        StringBuilder sb = new StringBuilder(t);
        if (sb.length() < 32) {
            int left = 32 - sb.length();
            while (left > 0) {
                sb.insert(0, "0");
                left--;
            }
        }
        return sb.toString();
    }

    public static void printPerfect32IntBinaryString(int x){
        String s = perfect32IntBinaryString(x);
        System.out.println(s);
    }

    public static void main(String[] args) {
        BitUtil.printPerfect32IntBinaryString(2);
        BitUtil.printPerfect32IntBinaryString(-1);
    }
}
