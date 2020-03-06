package cn.bj.brook.algorithm.array;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestFirstMissedPositiveZ {

    private FirstMissedPositiveZ func;

    public TestFirstMissedPositiveZ() {
        func = new FirstMissedPositiveZ();
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void test1() {
        int[] arr = new int[]{1, 2, 3, 4, 0};
        int i = func.firstMissingPositive2(arr);
        Assert.assertEquals(5, i);
    }

}
