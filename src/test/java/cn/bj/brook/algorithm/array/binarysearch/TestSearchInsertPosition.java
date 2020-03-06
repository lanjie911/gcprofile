package cn.bj.brook.algorithm.array.binarysearch;

import org.junit.Assert;
import org.junit.Test;

public class TestSearchInsertPosition {

    private SearchInsertPosition func;

    public TestSearchInsertPosition() {
        func = new SearchInsertPosition();
    }

    @Test
    public void test1() {
        int[] arr = new int[]{1, 3, 5, 6};
        int target = 5;
        int rs = func.searchInsert(arr, target);
        Assert.assertEquals(2, rs);
    }

    @Test
    public void test2() {
        int[] arr = new int[]{1, 3, 5, 6};
        int target = 2;
        int rs = func.searchInsert(arr, target);
        Assert.assertEquals(1, rs);
    }

    @Test
    public void test3() {
        int[] arr = new int[]{1, 3, 5, 6};
        int target = 7;
        int rs = func.searchInsert(arr, target);
        Assert.assertEquals(4, rs);
    }

    @Test
    public void test4() {
        int[] arr = new int[]{1, 3, 5, 6};
        int target = 0;
        int rs = func.searchInsert(arr, target);
        Assert.assertEquals(0, rs);
    }

    @Test
    public void test5() {
        int[] arr = new int[]{1};
        int target = 2;
        int rs = func.searchInsert(arr, target);
        Assert.assertEquals(1, rs);
    }

    @Test
    public void test6() {
        int[] arr = new int[]{2};
        int target = 1;
        int rs = func.searchInsert(arr, target);
        Assert.assertEquals(0, rs);
    }

    @Test
    public void test7() {
        int[] arr = new int[]{};
        int target = 1;
        int rs = func.searchInsert(arr, target);
        Assert.assertEquals(0, rs);
    }
}
