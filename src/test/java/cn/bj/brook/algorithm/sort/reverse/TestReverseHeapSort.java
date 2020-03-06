package cn.bj.brook.algorithm.sort.reverse;

import cn.bj.brook.algorithm.sort.SortFunction;
import org.junit.Assert;
import org.junit.Test;

public class TestReverseHeapSort {

    private SortFunction func;

    public TestReverseHeapSort(){
        func = new ReverseHeapSort();
    }

    @Test
    public void test1(){
        int[] arr = new int[]{3,5,1,-2,8};
        func.sort(arr);
        Assert.assertArrayEquals(new int[]{8,5,3,1,-2},arr);
    }

    @Test
    public void testEmpty(){
        int[] arr = new int[]{};
        func.sort(arr);
        Assert.assertArrayEquals(new int[]{},arr);
    }

    @Test
    public void test1Element(){
        int[] arr = new int[]{1};
        func.sort(arr);
        Assert.assertArrayEquals(new int[]{1},arr);
    }

    @Test
    public void test2Element(){
        int[] arr = new int[]{1,9};
        func.sort(arr);
        Assert.assertArrayEquals(new int[]{9,1},arr);
    }

    @Test
    public void test2SameElement(){
        int[] arr = new int[]{2,2};
        func.sort(arr);
        Assert.assertArrayEquals(new int[]{2,2},arr);
    }

    @Test
    public void testOnly1DiffElement(){
        int[] arr = new int[]{2,2,1,2,2};
        func.sort(arr);
        Assert.assertArrayEquals(new int[]{2,2,2,2,1},arr);
    }

}
