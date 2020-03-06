package cn.bj.brook.algorithm.sort.reverse;

import cn.bj.brook.algorithm.sort.SortFunction;
import org.junit.Assert;
import org.junit.Test;

public class TestReverseHeapSortInSelf {
    SortFunction func;

    public TestReverseHeapSortInSelf() {
        this.func = new ReverseHeapSortInSelf();
    }

    @Test
    public void test1() {
        int[] arr = new int[]{3, 1, 2, 6, -1, 2, 5};
        func.sort(arr);
        Assert.assertArrayEquals(new int[]{6, 5, 3, 2, 2, 1, -1}, arr);
    }

    @Test
    public void test2() {
        int[] arr = new int[]{1, 3};
        func.sort(arr);
        Assert.assertArrayEquals(new int[]{3, 1}, arr);
    }

    @Test
    public void test3() {
        int[] arr = new int[]{1};
        func.sort(arr);
        Assert.assertArrayEquals(new int[]{1}, arr);
    }
}
