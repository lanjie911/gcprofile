package cn.bj.brook.algorithm.array;

import org.junit.Assert;
import org.junit.Test;

public class TestMajorityNumberInArray {
    MajorityNumberInArray m ;

    public TestMajorityNumberInArray(){
        m = new MajorityNumberInArray();
    }

    @Test
    public void test1(){
        int[] arr = new int[]{3,2,3};
        int t = m.usingHashMap(arr);
        Assert.assertEquals(t,3);
    }

    @Test
    public void test2(){
        int[] arr = new int[]{2,2,2,1};
        int t = m.usingHashMap(arr);
        Assert.assertEquals(t,2);
    }

    @Test
    public void test3(){
        int[] arr = new int[]{1,1,0};
        int t = m.usingHashMap(arr);
        Assert.assertEquals(t,1);
    }

    @Test
    public void test4(){
        int[] arr = new int[]{6,6,6,7,7};
        int t = m.usingHashMap(arr);
        Assert.assertEquals(t,6);
    }

    @Test
    public void test5(){
        int[] arr = new int[]{6,6,6,7,7};
        int t = m.boyerMoore(arr);
        Assert.assertEquals(t,6);
    }
}
