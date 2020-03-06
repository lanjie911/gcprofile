package cn.bj.brook.algorithm.heap;

import cn.bj.brook.algorithm.sort.QuickSort;
import cn.bj.brook.algorithm.sort.SortFunction;
import cn.bj.brook.algorithm.sort.SortUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TestKSmallest {
    KthSmallest small;

    public TestKSmallest() {
        small = new KthSmallest();
    }

    @Test
    public void test1() {
        int[] inputs = new int[]{1, 3, 5, 7, 2, 4, 6, 8};
        int[] rs = small.smallestK(inputs, 4);
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4}, rs);
    }

    @Test
    public void test2() {
        int[] inputs = new int[]{1, 1, 2, 3, 5, 1, 2};
        int[] rs = small.smallestK(inputs, 4);
        Assert.assertArrayEquals(new int[]{1, 1, 1, 2}, rs);
    }

    @Test
    public void test3(){
        int[] inputs = SortUtil.generateWithMinus(Integer.MAX_VALUE,80000);
        long a = System.currentTimeMillis();
        int[] rs = small.smallestK(inputs, 49226);
        long b = System.currentTimeMillis();
        System.out.println(b-a);

        inputs = SortUtil.generateWithMinus(Integer.MAX_VALUE,80000);
        a = System.currentTimeMillis();
        Arrays.sort(inputs);
        Arrays.copyOfRange(inputs,0,49226);
        b = System.currentTimeMillis();
        System.out.println(b-a);

        inputs = SortUtil.generateWithMinus(Integer.MAX_VALUE,80000);
        SortFunction func = new QuickSort();
        a = System.currentTimeMillis();
        func.sort(inputs);
        Arrays.copyOfRange(inputs,0,49226);
        b = System.currentTimeMillis();
        System.out.println(b-a);


    }

}
