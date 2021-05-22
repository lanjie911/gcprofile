package cn.bj.brook.algorithm.binarysearch;

import java.util.Arrays;

// K绳分割
public class KLineSplit {
    public static double findAppropriateLength(int n, int k, double[] lines) {
        Arrays.sort(lines);
        double min = lines[0];
        double max = lines[lines.length - 1];
        double mid = (min + max) / 2;
        double acc = 0.01;
        double total = Arrays.stream(lines).sum();
        while (mid * k > total) {
            max = mid;
            mid = (min + max) / 2;
        }
        mid = (min + max) / 2;
        return mid;
    }

    // 是否满足精度
    public static boolean isMeetAccuracy(double lenNow, double ruler, double acc, int k, double total) {
        return (Math.abs(lenNow - ruler) < acc) && (lenNow * k < total) ? true : false;
    }

    public static double mySqrt(double x) {
        double k = 1.0;

        while (Math.abs(x - k * k) > 1e-9) {
            k = (k + x / k) / 2.0;
        }

        return k;
    }

    public static void main(String[] args) {
        //double rs = findAppropriateLength(4, 11, new double[]{8.02, 7.43, 4.57, 5.39});
        //System.out.println(rs);
        double rs = mySqrt(9.0);
        System.out.println(rs);

    }
}
