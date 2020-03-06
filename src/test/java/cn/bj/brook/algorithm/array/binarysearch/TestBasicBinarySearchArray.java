package cn.bj.brook.algorithm.array.binarysearch;

import org.junit.Assert;
import org.junit.Test;

public class TestBasicBinarySearchArray {

    private SearchRotateAscArray obj;

    public TestBasicBinarySearchArray() {
        obj = new SearchRotateAscArray();
    }

    @Test
    public void test1() {
        int[] nums = new int[]{1, 3, 4, 5, 10, 11, 18};
        int k = obj.search(nums, 5);
        Assert.assertEquals(3, k);
    }

    @Test
    public void test2() {
        int[] nums = new int[]{2};
        int k = obj.search(nums, 2);
        Assert.assertEquals(0, k);
    }

    @Test
    public void test3() {
        int[] nums = new int[]{1,3};
        int k = obj.search(nums, 3);
        Assert.assertEquals(1, k);
    }

    @Test
    public void test4() {
        int[] nums = new int[]{1,3,4};
        int k = obj.search(nums, 3);
        Assert.assertEquals(1, k);
    }

    @Test
    public void test5() {
        int[] nums = new int[]{1,3,4};
        int k = obj.search(nums, 1);
        Assert.assertEquals(0, k);
    }

    @Test
    public void test6() {
        int[] nums = new int[]{1,3,4};
        int k = obj.search(nums, 4);
        Assert.assertEquals(2, k);
    }

    @Test
    public void test7() {
        int[] nums = new int[]{1,3,5,6};
        int k = obj.search(nums, 4);
        Assert.assertEquals(-1, k);
    }

    @Test
    public void test8() {
        int[] nums = new int[]{1};
        int k = obj.search(nums, 2);
        Assert.assertEquals(-1, k);
    }

    @Test
    public void test9() {
        int[] nums = new int[]{1,2};
        int k = obj.search(nums, 3);
        Assert.assertEquals(-1, k);
    }

    @Test
    public void test10() {
        int[] nums = new int[]{1,2,3};
        int k = obj.search(nums, 5);
        Assert.assertEquals(-1, k);
    }

    @Test
    public void test11() {
        int[] nums = new int[]{1,2,3};
        int k = obj.search(nums, 3);
        Assert.assertEquals(2, k);
    }

    @Test
    public void test12() {
        int[] nums = new int[]{3,9};
        int k = obj.search(nums, 9);
        Assert.assertEquals(1, k);
    }
}
