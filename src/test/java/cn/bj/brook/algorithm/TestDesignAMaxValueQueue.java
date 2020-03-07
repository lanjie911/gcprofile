package cn.bj.brook.algorithm;

import org.junit.Assert;
import org.junit.Test;

public class TestDesignAMaxValueQueue {
    private DesignAMaxValueQueue func;

    public TestDesignAMaxValueQueue(){
        func = new DesignAMaxValueQueue();
    }

    @Test
    public void test1(){
        func.push_back(1);
        func.push_back(3);
        func.push_back(2);
        func.push_back(5);
        func.push_back(-1);
        int m = func.max_value();
        Assert.assertEquals(5, m);
        int f = func.pop_front();
        Assert.assertEquals(1, f);
        m = func.max_value();
        Assert.assertEquals(5, m);
    }

    @Test
    public void test2(){
        int t = func.pop_front();
        Assert.assertEquals(-1, t);
        int m = func.max_value();
        Assert.assertEquals(-1, m);
    }
}
