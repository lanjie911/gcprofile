package cn.bj.brook.algorithm.heap;

import cn.bj.brook.algorithm.slidingwindow.MaxValueFromSlidingWindowInArray;
import org.junit.Assert;
import org.junit.Test;

public class TestMaxValueFromSlidingWindowInArray {
    private MaxValueFromSlidingWindowInArray ma ;

    public TestMaxValueFromSlidingWindowInArray(){
        ma = new MaxValueFromSlidingWindowInArray();
    }

    @Test
    public void test1ElementWith1(){
        int[] a = new int[]{1};
        int k = 1;
        int[] b = ma.maxSlidingWindow(a,k);
        Assert.assertArrayEquals(new int[]{1},b);
    }

    @Test
    public void test2ElementWith1(){
        int[] a = new int[]{1,-3};
        int k = 1;
        int[] b = ma.maxSlidingWindow(a,k);
        Assert.assertArrayEquals(new int[]{1,-3},b);
    }

    @Test
    public void test2ElementWith2(){
        int[] a = new int[]{1,-3};
        int k = 2;
        int[] b = ma.maxSlidingWindow(a,k);
        Assert.assertArrayEquals(new int[]{1},b);
    }

    @Test
    public void test3ElementWith1(){
        int[] a = new int[]{1,-3,8};
        int k = 1;
        int[] b = ma.maxSlidingWindow(a,k);
        Assert.assertArrayEquals(new int[]{1,-3,8},b);
    }

    @Test
    public void test3ElementWith2(){
        int[] a = new int[]{1,-3,8};
        int k = 2;
        int[] b = ma.maxSlidingWindow(a,k);
        Assert.assertArrayEquals(new int[]{1,8},b);
    }

    @Test
    public void test3ElementWith3(){
        int[] a = new int[]{1,-3,8};
        int k = 3;
        int[] b = ma.maxSlidingWindow(a,k);
        Assert.assertArrayEquals(new int[]{8},b);
    }
}
