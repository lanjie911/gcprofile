package cn.bj.brook.algorithm.math;

import org.junit.Assert;
import org.junit.Test;

public class TestCalculator {
    private Calculator calc;

    public TestCalculator() {
        calc = new Calculator();
    }

    @Test
    public void test1() {
        String t = "1 +3 * 5";
        int r = calc.evaluate(t);
        Assert.assertEquals(16, r);
    }

    @Test
    public void test2() {
        String t = "20 * 10 +   13 * 15";
        int r = calc.evaluate(t);
        Assert.assertEquals(395, r);
    }

    @Test
    public void test3() {
        String t = "200";
        int r = calc.evaluate(t);
        Assert.assertEquals(200, r);
    }

    @Test
    public void test4() {
        String t = "9 * 12 * 13 + 1 + 2 * 5";
        int r = calc.evaluate(t);
        Assert.assertEquals(1415, r);
    }

    @Test
    public void test5() {
        String t = "1+    2";
        int r = calc.evaluate(t);
        Assert.assertEquals(3, r);
    }

    @Test
    public void test6() {
        String t = "   2*    3";
        int r = calc.evaluate(t);
        Assert.assertEquals(6, r);
    }

    @Test
    public void test7() {
        String t = "   2*    18";
        int r = calc.evaluate(t);
        Assert.assertEquals(36, r);
    }

    @Test
    public void test8() {
        String t = "   82*    2";
        int r = calc.evaluate(t);
        Assert.assertEquals(164, r);
    }

    @Test
    public void test9() {
        String t = "   1+2+  3   +4+5";
        int r = calc.evaluate(t);
        Assert.assertEquals(15, r);
    }
}
