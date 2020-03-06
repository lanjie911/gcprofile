package cn.bj.brook.algorithm.math;

import org.junit.Assert;
import org.junit.Test;

public class TestArrayAddOne {
    private ArrayAddOne func;

    public TestArrayAddOne() {
        func = new ArrayAddOne();
    }

    @Test
    public void test1() {
        int[] arr = new int[]{1, 2, 3};
        int[] rs = func.plusOne(arr);
        Assert.assertArrayEquals(new int[]{1, 2, 4}, rs);
    }

    @Test
    public void test2() {
        int[] arr = new int[]{1};
        int[] rs = func.plusOne(arr);
        Assert.assertArrayEquals(new int[]{2}, rs);
    }

    @Test
    public void test3() {
        int[] arr = new int[]{9};
        int[] rs = func.plusOne(arr);
        Assert.assertArrayEquals(new int[]{1, 0}, rs);
    }

    @Test
    public void test4() {
        int[] arr = new int[]{9, 9};
        int[] rs = func.plusOne(arr);
        Assert.assertArrayEquals(new int[]{1, 0, 0}, rs);
    }

    @Test
    public void test5() {
        int[] arr = new int[]{1,0,9};
        int[] rs = func.plusOne(arr);
        Assert.assertArrayEquals(new int[]{1, 1, 0}, rs);
    }
}
