package cn.bj.brook.algorithm.array;

import org.junit.Assert;
import org.junit.Test;

public class TestFindStartAndEndOfNumber2 {
    private FindStartAndEndOfNumber f;

    public TestFindStartAndEndOfNumber2() {
        f = new FindStartAndEndOfNumber();
    }

    @Test
    public void testEmpty() {
        int[] arr = new int[]{};
        int[] rs = f.searchRange(arr, 8);
        Assert.assertArrayEquals(rs, new int[]{-1, -1});
    }

    @Test
    public void test1Element() {
        int[] arr = new int[]{1};
        int[] rs = f.searchRange(arr, 1);
        Assert.assertArrayEquals(rs, new int[]{0, 0});
    }

    @Test
    public void test1ElementNotMatch() {
        int[] arr = new int[]{1};
        int[] rs = f.searchRange(arr, 3);
        Assert.assertArrayEquals(rs, new int[]{-1, -1});
    }

    @Test
    public void test2Element() {
        int[] arr = new int[]{1, 2};
        int[] rs = f.searchRange(arr, 2);
        Assert.assertArrayEquals(rs, new int[]{1, 1});
    }

    @Test
    public void test2ElementHead() {
        int[] arr = new int[]{1, 2};
        int[] rs = f.searchRange(arr, 1);
        Assert.assertArrayEquals(rs, new int[]{0, 0});
    }

    @Test
    public void test1() {
        int[] arr = new int[]{1, 2, 5, 8, 9, 10};
        int[] rs = f.searchRange(arr, 5);
        Assert.assertArrayEquals(rs, new int[]{2, 2});
    }

    @Test
    public void test2() {
        int[] arr = new int[]{1, 1, 2, 5, 8, 9, 10};
        int[] rs = f.searchRange(arr, 1);
        Assert.assertArrayEquals(rs, new int[]{0, 1});
    }

    @Test
    public void test3() {
        int[] arr = new int[]{1, 1, 2, 5, 8, 9, 10, 10};
        int[] rs = f.searchRange(arr, 10);
        Assert.assertArrayEquals(rs, new int[]{6, 7});
    }

    @Test
    public void test4() {
        int[] arr = new int[]{0, 1, 2, 3, 4, 5, 5, 6, 7};
        int[] rs = f.searchRange(arr, 5);
        Assert.assertArrayEquals(rs, new int[]{5, 6});
    }

    @Test
    public void test5() {
        int[] arr = new int[]{1, 2, 3};
        int[] rs = f.searchRange(arr, 2);
        Assert.assertArrayEquals(rs, new int[]{1, 1});
    }

    @Test
    public void testBorderLeft() {
        int[] arr = new int[]{1, 1, 1, 2, 3, 4};
        int[] rs = f.searchRange(arr, 1);
        Assert.assertArrayEquals(rs, new int[]{0, 2});
    }

    @Test
    public void testBorderRight() {
        int[] arr = new int[]{1, 2, 3, 4, 4, 4};
        int[] rs = f.searchRange(arr, 4);
        Assert.assertArrayEquals(rs, new int[]{3, 5});
    }

    @Test
    public void testBorderLeft2() {
        int[] arr = new int[]{1, 1, 2};
        int[] rs = f.searchRange(arr, 1);
        Assert.assertArrayEquals(rs, new int[]{0, 1});
    }

    @Test
    public void testBorderRight2() {
        int[] arr = new int[]{1, 4, 4};
        int[] rs = f.searchRange(arr, 4);
        Assert.assertArrayEquals(rs, new int[]{1, 2});
    }

    @Test
    public void testAll() {
        int[] arr = new int[]{1, 1};
        int[] rs = f.searchRange(arr, 1);
        Assert.assertArrayEquals(rs, new int[]{0, 1});
    }

    @Test
    public void testAll2() {
        int[] arr = new int[]{1, 1, 1};
        int[] rs = f.searchRange(arr, 1);
        Assert.assertArrayEquals(rs, new int[]{0, 2});
    }


    @Test
    public void testAll3() {
        int[] arr = new int[]{1, 1, 1, 1};
        int[] rs = f.searchRange(arr, 1);
        Assert.assertArrayEquals(rs, new int[]{0, 3});
    }

    @Test
    public void test6() {
        int[] arr = new int[]{5, 7, 7, 8, 8, 10};
        int[] rs = f.searchRange(arr, 6);
        Assert.assertArrayEquals(rs, new int[]{-1, -1});
    }
}
