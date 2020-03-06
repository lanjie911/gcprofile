package cn.bj.brook.algorithm.greedy;

import org.junit.Assert;
import org.junit.Test;

public class TestNOilLoopProblem {
    private NOilLoopProblem func;

    public TestNOilLoopProblem() {
        func = new NOilLoopProblem();
    }

    @Test
    public void test1() {
        int[] gas = new int[]{1, 2, 3, 4, 5};
        int[] cost = new int[]{3, 4, 5, 1, 2};
        int r = func.canCompleteCircuit(gas, cost);
        Assert.assertEquals(3, r);
    }

    @Test
    public void test2() {
        int[] gas = new int[]{2, 3, 4};
        int[] cost = new int[]{3, 4, 3};
        int r = func.canCompleteCircuit(gas, cost);
        Assert.assertEquals(-1, r);
    }

    @Test
    public void test3() {
        int[] gas = new int[]{5, 8, 2, 8};
        int[] cost = new int[]{6, 5, 6, 6};
        int r = func.canCompleteCircuit(gas, cost);
        Assert.assertEquals(3, r);
    }

    @Test
    public void test4() {
        int[] gas = new int[]{5, 5, 1, 3, 4};
        int[] cost = new int[]{8, 1, 7, 1, 1};
        int r = func.canCompleteCircuit(gas, cost);
        Assert.assertEquals(3, r);
    }

    @Test
    public void test5() {
        int[] gas = new int[]{2};
        int[] cost = new int[]{2};
        int r = func.canCompleteCircuit(gas, cost);
        Assert.assertEquals(0, r);
    }
}
