package cn.bj.brook.algorithm;

import org.junit.Test;

public class TestPrintSequentialArraySumEqualTarget {

    private PrintSequentialArraySumEqualTarget func;

    public TestPrintSequentialArraySumEqualTarget(){
        func = new PrintSequentialArraySumEqualTarget();
    }

    @Test
    public void test1(){
        int[][] rs = func.findContinuousSequence(9);
    }

    @Test
    public void test2(){
        int[][] rs = func.findContinuousSequence(15);
    }

    @Test
    public void test3(){
        int[][] rs = func.findContinuousSequence(1);
    }

    @Test
    public void test4(){
        int[][] rs = func.findContinuousSequence(2);
    }

    @Test
    public void test5(){
        int[][] rs = func.findContinuousSequence(3);
    }

    @Test
    public void test6(){
        int[][] rs = func.resolution2(4);
    }

    @Test
    public void test7(){
        int[][] rs = func.findContinuousSequence(5);
    }

    @Test
    public void test8(){
        int[][] rs = func.resolution2(50252);
    }
}
