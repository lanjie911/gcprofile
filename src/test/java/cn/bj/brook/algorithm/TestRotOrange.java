package cn.bj.brook.algorithm;

import org.junit.Assert;
import org.junit.Test;

public class TestRotOrange {
    private RotOrange func;

    public TestRotOrange() {
        func = new RotOrange();
    }

    @Test
    public void test1() {
        int[][] oranges = new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        int c = func.orangesRotting(oranges);
        Assert.assertEquals(4, c);
    }

    @Test
    public void test2() {
        int[][] oranges = new int[][]{{0, 2}};
        int c = func.orangesRotting(oranges);
        Assert.assertEquals(0, c);
    }

    @Test
    public void test3() {
        int[][] oranges = new int[][]{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}};
        int c = func.orangesRotting(oranges);
        Assert.assertEquals(-1, c);
    }
}
