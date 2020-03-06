package cn.bj.brook.algorithm.math;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestPrintYangHuiTriangle {
    private PrintYangHuiTriangle func;

    public TestPrintYangHuiTriangle(){
        func = new PrintYangHuiTriangle();
    }

    @Test
    public void test1(){
        int n = 1;
        List<List<Integer>> rs = func.generate(n);
        Assert.assertEquals(1,rs.size());
    }

    @Test
    public void test2(){
        int n = 2;
        List<List<Integer>> rs = func.generate(n);
        Assert.assertEquals(2,rs.size());
    }

    @Test
    public void test3(){
        int n = 3;
        List<List<Integer>> rs = func.generate(n);
        Assert.assertEquals(3,rs.size());
    }

    @Test
    public void test4(){
        int n = 4;
        List<List<Integer>> rs = func.generate(n);
        Assert.assertEquals(4, rs.size());
    }

    @Test
    public void test5(){
        int n = 5;
        List<List<Integer>> rs = func.generate(n);
        Assert.assertEquals(5, rs.size());
    }
}
