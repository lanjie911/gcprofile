package cn.bj.brook.algorithm;

import org.junit.Assert;
import org.junit.Test;

public class TestCountNumber1 {
    private CountNumber1 func;

    public TestCountNumber1(){
        func = new CountNumber1();
    }

    @Test
    public void test1(){
        int n = 13;
        int r = func.countDigitOne(n);
        Assert.assertEquals(6, r);
    }

    @Test
    public void test2(){
        int n = 21;
        int r = func.countDigitOne(n);
        Assert.assertEquals(13, r);
    }

    @Test
    public void test3(){
        int n = 32;
        int r = func.countDigitOne(n);
        Assert.assertEquals(14, r);
    }

    @Test
    public void test4(){
        int n = 99;
        int r = func.countDigitOne(n);
        Assert.assertEquals(20, r);
    }

    @Test
    public void test5(){
        int n = 103;
        int r = func.countDigitOne(n);
        Assert.assertEquals(25, r);
    }

    @Test
    public void test6(){
        int n = 824883294;
        int r = func.countDigitOne(n);
        Assert.assertEquals(767944060, r);
    }
}
