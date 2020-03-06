package cn.bj.brook.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

public class SortUtil {

    public static int[] generate(int maxBound, int arraySize){
        if(maxBound <= 0 || arraySize <= 0){
            throw new RuntimeException("bound and size must be greater than 0");
        }
        int[] arrs = new int[arraySize];
        for(int i=0;i<arraySize;i++){
            arrs[i] = generateOne(maxBound,true);
        }
        return  arrs;
    }

    public static int[] generateWithMinus(int maxBound, int arraySize){
        if(maxBound <= 0 || arraySize <= 0){
            throw new RuntimeException("bound and size must be greater than 0");
        }
        int[] arrs = new int[arraySize];
        for(int i=0;i<arraySize;i++){
            arrs[i] = generateOne(maxBound,false);
        }
        return  arrs;
    }

    private static int generateOne(int bound,boolean isPositive){
        Random rand = new Random();
        int temp = rand.nextInt(bound);

        if(isPositive){
            return temp;
        }else{
            boolean sign = rand.nextInt(10) >= 5;
            return sign? temp:-temp;
        }
    }

    public static String toString(int[] array){
        return Arrays.toString(array);
    }

    public static void print(int[] array){
        System.out.println(toString(array));
    }
}
