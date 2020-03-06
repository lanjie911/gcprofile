package cn.bj.brook.algorithm.math;

import org.junit.Assert;
import org.junit.Test;

public class TestLongStringMultiply3 {
    private LongStringMultiply func;

    public TestLongStringMultiply3() {
        func = new LongStringMultiply();
    }

    @Test
    public void test1() {
        String a = "3";
        String b = "6";
        String r = func.longStringMultiplySingleNumber(a, b);
        Assert.assertEquals("18", r);
    }

    @Test
    public void test2() {
        String a = "24";
        String b = "6";
        String r = func.longStringMultiplySingleNumber(a, b);
        Assert.assertEquals("144", r);
    }

    @Test
    public void test3() {
        String a = "88591023776641344";
        String b = "3";
        String r = func.longStringMultiplySingleNumber(a, b);
        Assert.assertEquals("265773071329924032", r);
    }

    @Test
    public void test4() {
        String a = "88591023776641344";
        int b = 0;
        String r = func.longStringMultiply10Times(a, b);
        Assert.assertEquals("88591023776641344", r);
    }

    @Test
    public void test5() {
        String a = "88591023776641344";
        int b = 2;
        String r = func.longStringMultiply10Times(a, b);
        Assert.assertEquals("8859102377664134400", r);
    }
}
