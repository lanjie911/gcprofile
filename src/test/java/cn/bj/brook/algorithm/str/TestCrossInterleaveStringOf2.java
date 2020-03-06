package cn.bj.brook.algorithm.str;

import org.junit.Assert;
import org.junit.Test;

public class TestCrossInterleaveStringOf2 {
    private CrossInterleaveStringOf2 func;

    public TestCrossInterleaveStringOf2() {
        func = new CrossInterleaveStringOf2();
    }

    @Test
    public void test1() {
        String s1 = "abcdef";
        String s2 = "123456";
        String s3 = "a1b2c3d4e5f6";
        boolean f = func.isInterleave(s1, s2, s3);
        Assert.assertEquals(true, f);
    }

    @Test
    public void test2() {
        String s1 = "abcd";
        String s2 = "12";
        String s3 = "ab1c2d";
        boolean f = func.isInterleave(s1, s2, s3);
        Assert.assertEquals(true, f);
    }

    @Test
    public void test3() {
        String s1 = "12";
        String s2 = "abcd";
        String s3 = "ab1c2d";
        boolean f = func.isInterleave(s1, s2, s3);
        Assert.assertEquals(true, f);
    }

    @Test
    public void test4() {
        String s1 = "123";
        String s2 = "ab";
        String s3 = "a1b23";
        boolean f = func.isInterleave(s1, s2, s3);
        Assert.assertEquals(true, f);
    }

    @Test
    public void test5() {
        String s1 = "ab";
        String s2 = "123";
        String s3 = "1a2b3";
        boolean f = func.isInterleave(s1, s2, s3);
        Assert.assertEquals(true, f);
    }

    @Test
    public void test6() {
        String s1 = "ab";
        String s2 = "1";
        String s3 = "a1b";
        boolean f = func.isInterleave(s1, s2, s3);
        Assert.assertEquals(true, f);
    }

    @Test
    public void test7() {
        String s1 = "ab";
        String s2 = "1";
        String s3 = "ab1";
        boolean f = func.isInterleave(s1, s2, s3);
        Assert.assertEquals(false, f);
    }

    @Test
    public void test8() {
        String s1 = "abc";
        String s2 = "1";
        String s3 = "a1bc";
        boolean f = func.isInterleave(s1, s2, s3);
        Assert.assertEquals(true, f);
    }

    @Test
    public void test9() {
        String s1 = "abc";
        String s2 = "1";
        String s3 = "ab1c";
        boolean f = func.isInterleave(s1, s2, s3);
        Assert.assertEquals(true, f);
    }

    @Test
    public void test10() {
        String s1 = "1";
        String s2 = "abc";
        String s3 = "ab1c";
        boolean f = func.isInterleave(s1, s2, s3);
        Assert.assertEquals(true, f);
    }

    @Test
    public void test11() {
        String s1 = "12";
        String s2 = "abcd";
        String s3 = "ab1c2d";
        boolean f = func.isInterleave(s1, s2, s3);
        Assert.assertEquals(true, f);
    }

    @Test
    public void test12() {
        String s1 = "12";
        String s2 = "ab";
        String s3 = "1a2b";
        boolean f = func.isInterleave(s1, s2, s3);
        Assert.assertEquals(true, f);
    }

    @Test
    public void test13() {
        String s1 = "12";
        String s2 = "ab";
        String s3 = "a1b2";
        boolean f = func.isInterleave(s1, s2, s3);
        Assert.assertEquals(true, f);
    }

    @Test
    public void test14() {
        String s1 = "1";
        String s2 = "a";
        String s3 = "1a";
        boolean f = func.isInterleave(s1, s2, s3);
        Assert.assertEquals(false, f);
    }

    @Test
    public void test15() {
        String s1 = "ab";
        String s2 = "bc";
        String s3 = "babc";
        boolean f = func.isInterleave(s1, s2, s3);
        Assert.assertEquals(true, f);
    }

}
