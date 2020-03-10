package cn.bj.brook.algorithm;

import org.junit.Assert;
import org.junit.Test;

public class TestMissTwoNatureNumbers {
    MissTwoNatureNumbers func;

    public TestMissTwoNatureNumbers() {
        func = new MissTwoNatureNumbers();
    }

    @Test
    public void test1() {
        int[] arr = new int[]{1};
        int[] rs = func.missingTwo(arr);
        Assert.assertArrayEquals(new int[]{2, 3}, rs);
    }

    @Test
    public void test2() {
        int[] arr = new int[]{1, 2};
        int[] rs = func.missingTwo(arr);
        Assert.assertArrayEquals(new int[]{3, 4}, rs);
    }

    @Test
    public void test3() {
        int[] arr = new int[]{1, 2, 3};
        int[] rs = func.missingTwo(arr);
        Assert.assertArrayEquals(new int[]{4, 5}, rs);
    }

    @Test
    public void test4() {
        int[] arr = new int[]{2, 3};
        int[] rs = func.missingTwo(arr);
        Assert.assertArrayEquals(new int[]{1, 4}, rs);
    }

    @Test
    public void test5() {
        int[] arr = new int[]{2, 3, 4};
        int[] rs = func.missingTwo(arr);
        Assert.assertArrayEquals(new int[]{1, 5}, rs);
    }

    @Test
    public void test6() {
        int[] arr = new int[]{2, 3, 4, 5};
        int[] rs = func.missingTwo(arr);
        Assert.assertArrayEquals(new int[]{1, 6}, rs);
    }

    @Test
    public void test7() {
        int[] arr = new int[]{3};
        int[] rs = func.missingTwo(arr);
        Assert.assertArrayEquals(new int[]{1, 2}, rs);
    }

    @Test
    public void test8() {
        int[] arr = new int[]{3, 4, 5};
        int[] rs = func.missingTwo(arr);
        Assert.assertArrayEquals(new int[]{1, 2}, rs);
    }

    @Test
    public void test9() {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        int[] rs = func.missingTwo(arr);
        Assert.assertArrayEquals(new int[]{6, 7}, rs);
    }

    @Test
    public void test10() {
        int[] arr = new int[]{2, 4, 5};
        int[] rs = func.missingTwo(arr);
        Assert.assertArrayEquals(new int[]{1, 3}, rs);
    }

    @Test
    public void test11() {
        int[] arr = new int[]{1, 4, 5};
        int[] rs = func.missingTwo(arr);
        Assert.assertArrayEquals(new int[]{2, 3}, rs);
    }

    @Test
    public void test12() {
        int[] arr = new int[]{1, 3, 4, 6};
        int[] rs = func.missingTwo(arr);
        Assert.assertArrayEquals(new int[]{2, 5}, rs);
    }

    @Test
    public void test13() {
        int[] arr = new int[]{1, 3, 4, 5};
        int[] rs = func.missingTwo(arr);
        Assert.assertArrayEquals(new int[]{2, 6}, rs);
    }

    @Test
    public void test14() {
        int[] arr = new int[]{1, 2, 4};
        int[] rs = func.missingTwo(arr);
        Assert.assertArrayEquals(new int[]{3, 5}, rs);
    }

    @Test
    public void test15() {
        int[] arr = new int[]{2};
        int[] rs = func.missingTwo(arr);
        Assert.assertArrayEquals(new int[]{1, 3}, rs);
    }
}
