package cn.bj.brook.algorithm.sort;

import org.junit.Assert;
import org.junit.Test;

public class TestMergeSort {

    private SortFunction func;

    public TestMergeSort() {
        func = new MergeSort();
    }

    @Test
    public void test1() {
        int[] arr = new int[]{3, 2, 1, 9, 4};
        func.sort(arr);
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 9}, arr);
    }

}
