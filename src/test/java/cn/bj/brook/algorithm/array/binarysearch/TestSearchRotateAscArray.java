package cn.bj.brook.algorithm.array.binarysearch;

import org.junit.Assert;
import org.junit.Test;

public class TestSearchRotateAscArray {
    private SearchRotateAscArray searcher;

    public TestSearchRotateAscArray() {
        searcher = new SearchRotateAscArray();
    }

    @Test
    public void test01() {
        int[] arr = new int[]{13, 14, 15, 0, 1, 2};
        int pos = searcher.search(arr, 15);
        Assert.assertEquals(2, pos);
    }

    @Test
    public void test02() {
        int[] arr = new int[]{13, 14, 15, 0, 1, 2};
        int pos = searcher.search(arr, 13);
        Assert.assertEquals(0, pos);
    }

    @Test
    public void test03() {
        int[] arr = new int[]{13, 14, 15, 0, 1, 2};
        int pos = searcher.search(arr, 2);
        Assert.assertEquals(5, pos);
    }

    @Test
    public void test04() {
        int[] arr = new int[]{13};
        int pos = searcher.search(arr, 13);
        Assert.assertEquals(0, pos);
    }

    @Test
    public void test05() {
        int[] arr = new int[]{13, 15};
        int pos = searcher.search(arr, 13);
        Assert.assertEquals(0, pos);
    }

    @Test
    public void test06() {
        int[] arr = new int[]{13, 15};
        int pos = searcher.search(arr, 15);
        Assert.assertEquals(1, pos);
    }

    @Test
    public void test07() {
        int[] arr = new int[]{13};
        int pos = searcher.search(arr, 8);
        Assert.assertEquals(-1, pos);
    }

    @Test
    public void test08() {
        int[] arr = new int[]{13, 14, 15, 0, 1, 2};
        int pos = searcher.search(arr, 8);
        Assert.assertEquals(-1, pos);
    }

    @Test
    public void test09() {
        int[] arr = new int[]{5, 8, 0, 1, 3, 4};
        int pos = searcher.search(arr, 8);
        Assert.assertEquals(1, pos);
    }

    @Test
    public void test10() {
        int[] arr = new int[]{5, 8, 0, 1, 3, 4};
        int pos = searcher.search(arr, 3);
        Assert.assertEquals(4, pos);
    }

    @Test
    public void test11() {
        int[] arr = new int[]{9,12,15,20,22,4,5};
        int pos = searcher.search(arr, 4);
        Assert.assertEquals(5, pos);
    }

    @Test
    public void test12() {
        int[] arr = new int[]{3,1};
        int pos = searcher.search(arr, 0);
        Assert.assertEquals(-1, pos);
    }

}
