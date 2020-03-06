package cn.bj.brook.algorithm.array;

import org.junit.Assert;
import org.junit.Test;

public class TestLongestSequentialSubArray {

    private LongestSequentialSubArray obj;

    public TestLongestSequentialSubArray(){
        obj = new LongestSequentialSubArray();
    }

    @Test
    public void test1(){
        int k = obj.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2});
        Assert.assertEquals(4,k);
    }

    @Test
    public void testEmpty(){
        int k = obj.longestConsecutive(new int[]{});
        Assert.assertEquals(0,k);
    }

    @Test
    public void testOnly1Element(){
        int k = obj.longestConsecutive(new int[]{2});
        Assert.assertEquals(1,k);
    }

    @Test
    public void testOnly2Element1(){
        int k = obj.longestConsecutive(new int[]{2,3});
        Assert.assertEquals(2,k);
    }

    @Test
    public void test2(){
        int k = obj.longestConsecutive(new int[]{-10,21,5,8,-5,-9,-4,-3,-2,-1,0,9,7});
        Assert.assertEquals(6,k);
    }
}
