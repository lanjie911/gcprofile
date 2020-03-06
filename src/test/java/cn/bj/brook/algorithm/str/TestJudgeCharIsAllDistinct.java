package cn.bj.brook.algorithm.str;

import cn.bj.brook.algorithm.bitoper.JudgeCharIsAllDistinct;
import org.junit.Assert;
import org.junit.Test;

public class TestJudgeCharIsAllDistinct {
    private JudgeCharIsAllDistinct func;

    public TestJudgeCharIsAllDistinct(){
        func = new JudgeCharIsAllDistinct();
    }

    @Test
    public void test1(){
        String str = "leetcode";
        boolean b = func.isUnique(str);
        Assert.assertEquals(false,b);
    }

    @Test
    public void test2(){
        String str = "abc";
        boolean b = func.isUnique(str);
        Assert.assertEquals(true,b);
    }

    @Test
    public void test3(){
        String str = "";
        boolean b = func.isUnique(str);
        Assert.assertEquals(true,b);
    }

    @Test
    public void test4(){
        String str = "leetcode";
        boolean b = func.isUniqueUsingBit(str);
        Assert.assertEquals(false,b);
    }

    @Test
    public void test5(){
        String str = "abc";
        boolean b = func.isUniqueUsingBit(str);
        Assert.assertEquals(true,b);
    }

    @Test
    public void test6(){
        String str = "";
        boolean b = func.isUniqueUsingBit(str);
        Assert.assertEquals(true,b);
    }
}
