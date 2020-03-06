package cn.bj.brook.algorithm.heap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestKLargest {

    private KthLargest k;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void test1() {
        k = new KthLargest(3, new int[]{4, 5, 8, 2});
        int t = k.add(3);
        Assert.assertEquals(4, t);
        t = k.add(5);
        Assert.assertEquals(5, t);
        t = k.add(10);
        Assert.assertEquals(5, t);
        t = k.add(9);
        Assert.assertEquals(8, t);
        t = k.add(4);
        Assert.assertEquals(8, t);
    }

    @Test
    public void test2() {
        k = new KthLargest(1, new int[]{});
        int t = k.add(-3);
        Assert.assertEquals(-3, t);
        t = k.add(-2);
        Assert.assertEquals(-2, t);
        t = k.add(-4);
        Assert.assertEquals(-2, t);
        t = k.add(0);
        Assert.assertEquals(0, t);
        t = k.add(4);
        Assert.assertEquals(4, t);
    }

    @Test
    public void test3() {
        k = new KthLargest(2, new int[]{0});
        int t = k.add(-1);
        Assert.assertEquals(-1, t);
        t = k.add(1);
        Assert.assertEquals(0, t);
        t = k.add(-2);
        Assert.assertEquals(0, t);
        t = k.add(-4);
        Assert.assertEquals(0, t);
        t = k.add(3);
        Assert.assertEquals(1, t);
    }

}
