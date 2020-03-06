package cn.bj.brook.algorithm.heap;

import org.junit.Assert;
import org.junit.Test;

public class TestLastStoneWeight {
    private LastStoneWeight w;

    public TestLastStoneWeight(){
        w = new LastStoneWeight();
    }

    @Test
    public void test1(){
        int k = w.lastStoneWeight(new int[]{1});
        Assert.assertEquals(1,k);
    }

    @Test
    public void test2(){
        int k = w.lastStoneWeight(new int[]{1,2});
        Assert.assertEquals(1,k);
    }

    @Test
    public void test3(){
        int k = w.lastStoneWeight(new int[]{1,2,3});
        Assert.assertEquals(0,k);
    }

    @Test
    public void test4(){
        int k = w.lastStoneWeight(new int[]{3,8,2,5});
        Assert.assertEquals(2,k);
    }

    @Test
    public void test5(){
        int k = w.lastStoneWeight(new int[]{10,10,5});
        Assert.assertEquals(5,k);
    }

    @Test
    public void test6(){
        int k = w.lastStoneWeight(new int[]{1,1,1});
        Assert.assertEquals(1,k);
    }

    @Test
    public void test7(){
        int k = w.lastStoneWeight(new int[]{3,7,2});
        Assert.assertEquals(2,k);
    }
}
