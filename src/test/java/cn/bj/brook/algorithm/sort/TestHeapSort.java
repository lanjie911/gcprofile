package cn.bj.brook.algorithm.sort;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestHeapSort {

    private SortFunction sf;
    private SortAlgorithmFrame frame;

    public TestHeapSort(){
        sf = new HeapSort();
    }

    @Before
    public void setUp(){
        frame = new SortAlgorithmFrame(100,8);
    }

    @Test
    public void test1(){
        frame.sort(this.sf);
    }

    @Test
    public void testEmptyArray(){
        int[] t = new int[]{};
        sf.sort(t);
    }

    @Test
    public void testOnly1ElementArray(){
        int[] t = new int[]{1};
        sf.sort(t);
        Assert.assertArrayEquals(new int[]{1},t);
    }

    @Test
    public void test2ElementsArray(){
        int[] t = new int[]{24,1};
        sf.sort(t);
        Assert.assertArrayEquals(new int[]{1,24},t);
    }

}
