package cn.bj.brook.algorithm.array;

import org.junit.Assert;
import org.junit.Test;

public class TestNextDictSortSequence {
    private NextDictSortSequence func;

    public TestNextDictSortSequence() {
        func = new NextDictSortSequence();
    }

    @Test
    public void test1() {
        int[] arr = new int[]{1, 3, 2, 1, 5, 1};
        func.nextPermutation(arr);
        Assert.assertArrayEquals(new int[]{1, 3, 2, 5, 1, 1}, arr);
    }

    @Test
    public void test2() {
        int[] arr = new int[]{3, 2, 1};
        func.nextPermutation(arr);
        Assert.assertArrayEquals(new int[]{1, 2, 3}, arr);
    }
}
