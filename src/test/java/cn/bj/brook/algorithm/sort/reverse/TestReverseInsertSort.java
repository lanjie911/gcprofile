package cn.bj.brook.algorithm.sort.reverse;

import cn.bj.brook.algorithm.sort.SortFunction;
import org.junit.Assert;
import org.junit.Test;

public class TestReverseInsertSort {

    private SortFunction func;

    public TestReverseInsertSort() {
        func = new ReverseInsertSort();
    }

    @Test
    public void test1() {
        int[] arr = new int[]{1, 4, -2, 5, 2};
        func.sort(arr);
        Assert.assertArrayEquals(new int[]{5, 4, 2, 1, -2}, arr);
    }

    @Test
    public void testEmpty() {
        int[] arr = new int[]{};
        func.sort(arr);
        Assert.assertArrayEquals(new int[]{}, arr);
    }

    @Test
    public void test1Element() {
        int[] arr = new int[]{1};
        func.sort(arr);
        Assert.assertArrayEquals(new int[]{1}, arr);
    }

    @Test
    public void test2Element() {
        int[] arr = new int[]{1, 4};
        func.sort(arr);
        Assert.assertArrayEquals(new int[]{4, 1}, arr);
    }

    @Test
    public void test2SameElement() {
        int[] arr = new int[]{4, 4};
        func.sort(arr);
        Assert.assertArrayEquals(new int[]{4, 4}, arr);
    }

}
