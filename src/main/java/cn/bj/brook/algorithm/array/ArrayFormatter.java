package cn.bj.brook.algorithm.array;

import java.util.Arrays;

public class ArrayFormatter {
    public static void print(int[][] para){
        int r = para.length;
        int c = para[0].length;
        // 打印结果二维数组maxValue
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.printf("%6d", para[i][j]);
            }
            System.out.println();
        }
    }

    public static String toString(int[] para){
        return Arrays.toString(para);
    }

    public static void print(int[] para){
        System.out.println(toString(para));
    }
}
