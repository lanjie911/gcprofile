package cn.bj.brook.algorithm;

import org.junit.Assert;
import org.junit.Test;

public class TestBinaryStringSum {

    private BinaryStringSum func;

    public TestBinaryStringSum() {
        func = new BinaryStringSum();
    }

    @Test
    public void test1() {
        String a = "0101";
        String b = "1011";
        String rs = func.addBinary(a, b);
        Assert.assertEquals("10000", rs);
    }

    @Test
    public void test2() {
        String a = "0";
        String b = "11011";
        String rs = func.addBinary(a, b);
        Assert.assertEquals("11011", rs);
    }

    @Test
    public void test3() {
        String a = "";
        String b = "11011";
        String rs = func.addBinary(a, b);
        Assert.assertEquals("11011", rs);
    }

    @Test
    public void test4() {
        String a = "1";
        String b = "1";
        String rs = func.addBinary(a, b);
        Assert.assertEquals("10", rs);
    }

    @Test
    public void test5() {
        String a = "1";
        String b = "11";
        String rs = func.addBinary(a, b);
        Assert.assertEquals("100", rs);
    }
}
