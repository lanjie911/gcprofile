package cn.bj.brook.algorithm;

import org.junit.Assert;
import org.junit.Test;

public class TestFacadeNumberSequence {

    private FacadeNumberSequence func;

    public TestFacadeNumberSequence(){
        func = new FacadeNumberSequence();
    }

    @Test
    public void test1(){
        int n = 1;
        String t = func.countAndSay(n);
        Assert.assertEquals("1", t);
    }

    @Test
    public void test2(){
        int n = 2;
        String t = func.countAndSay(n);
        Assert.assertEquals("11", t);
    }

    @Test
    public void test3(){
        int n = 3;
        String t = func.countAndSay(n);
        Assert.assertEquals("21", t);
    }

    @Test
    public void test4(){
        int n = 4;
        String t = func.countAndSay(n);
        Assert.assertEquals("1211", t);
    }

    @Test
    public void test5(){
        int n = 5;
        String t = func.countAndSay(n);
        Assert.assertEquals("111221", t);
    }

    @Test
    public void test6(){
        int n = 6;
        String t = func.countAndSay(n);
        Assert.assertEquals("312211", t);
    }

}
