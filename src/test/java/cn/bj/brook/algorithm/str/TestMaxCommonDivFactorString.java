package cn.bj.brook.algorithm.str;

import org.junit.Assert;
import org.junit.Test;

public class TestMaxCommonDivFactorString {
    private MaxCommonDivFactorString func;

    public TestMaxCommonDivFactorString() {
        func = new MaxCommonDivFactorString();
    }

    @Test
    public void test1() {
        String str1 = "ABCABC";
        String str2 = "ABC";
        String rs = func.gcdOfStrings(str1, str2);
        Assert.assertEquals("ABC", rs);
    }

    @Test
    public void test2() {
        String str1 = "ABABAB";
        String str2 = "ABAB";
        String rs = func.gcdOfStrings(str1, str2);
        Assert.assertEquals("AB", rs);
    }

    @Test
    public void test3() {
        String str1 = "LEET";
        String str2 = "CODE";
        String rs = func.gcdOfStrings(str1, str2);
        Assert.assertEquals("", rs);
    }
}
