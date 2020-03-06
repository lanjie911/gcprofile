package cn.bj.brook.algorithm.math;

import org.junit.Assert;
import org.junit.Test;

public class TestLongStringMultiply2 {
    private LongStringMultiply func;

    public TestLongStringMultiply2() {
        func = new LongStringMultiply();
    }

    @Test
    public void test1() {
        String a = "3";
        String b = "6";
        String r = func.multiply(a, b);
        Assert.assertEquals("18", r);
    }

    @Test
    public void test2() {
        String a = "0";
        String b = "6";
        String r = func.multiply(a, b);
        Assert.assertEquals("0", r);
    }

    @Test
    public void test3() {
        String a = "3";
        String b = "0";
        String r = func.multiply(a, b);
        Assert.assertEquals("0", r);
    }

    @Test
    public void test4() {
        String a = "5";
        String b = "5";
        String r = func.multiply(a, b);
        Assert.assertEquals("25", r);
    }

    @Test
    public void test5() {
        String a = "12";
        String b = "12";
        String r = func.multiply(a, b);
        Assert.assertEquals("144", r);
    }

    @Test
    public void test6() {
        String a = "498828660196";
        String b = "840477629533";
        String r = func.multiply(a, b);
        Assert.assertEquals("419254329864656431168468", r);
    }
}
