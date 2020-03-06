package cn.bj.brook.algorithm.math;

import org.junit.Assert;
import org.junit.Test;

public class TestLongStringMultiply1 {
    private LongStringMultiply func;

    public TestLongStringMultiply1() {
        func = new LongStringMultiply();
    }

    @Test
    public void test1() {
        String a = "123";
        String b = "23";
        String r = func.longStringAdd(a, b);
        Assert.assertEquals("146", r);
    }

    @Test
    public void test2() {
        String a = "12";
        String b = "0";
        String r = func.longStringAdd(a, b);
        Assert.assertEquals("12", r);
    }

    @Test
    public void test3() {
        String a = "1";
        String b = "0";
        String r = func.longStringAdd(a, b);
        Assert.assertEquals("1", r);
    }

    @Test
    public void test4() {
        String a = "111111111";
        String b = "1";
        String r = func.longStringAdd(a, b);
        Assert.assertEquals("111111112", r);
    }

    @Test
    public void test5() {
        String a = "999";
        String b = "99";
        String r = func.longStringAdd(a, b);
        Assert.assertEquals("1098", r);
    }

    @Test
    public void test6() {
        String a = "123456789012345678901234567890";
        String b = "987654321098765432109876543210";
        String r = func.longStringAdd(a, b);
        Assert.assertEquals("1111111110111111111011111111100", r);
    }


    @Test
    public void test7() {
        String a = "1";
        String b = "2";
        String r = func.longStringAdd(a, b);
        Assert.assertEquals("3", r);
    }
}
