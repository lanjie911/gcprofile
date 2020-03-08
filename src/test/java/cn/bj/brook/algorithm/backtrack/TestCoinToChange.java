package cn.bj.brook.algorithm.backtrack;

import org.junit.Assert;
import org.junit.Test;

public class TestCoinToChange {
    private CoinToChange func;

    public TestCoinToChange() {
        func = new CoinToChange();
    }

    @Test
    public void test1() {
        int[] arr = new int[]{1, 2, 5};
        int n = func.coinChange(arr, 11);
        Assert.assertEquals(3, n);
    }

    @Test
    public void test2() {
        int[] arr = new int[]{27, 40, 244, 168, 383};
        int n = func.coinChange(arr, 6989);
        Assert.assertEquals(23, n);
    }

    @Test
    public void test3() {
        int[] arr = new int[]{27, 40, 244, 168, 383};
        int n = func.coinChange2(arr, 6989);
        Assert.assertEquals(23, n);
    }
}
