package cn.bj.brook.algorithm;

import org.junit.Assert;
import org.junit.Test;

/**
 * 测试喝啤酒的case
 */
public class TestHowMuchBeers {

    private HowMuchBeers beers;

    public TestHowMuchBeers() {
        beers = new HowMuchBeers();
    }

    @Test
    public void test1() {
        int max = beers.maxBeers(10, 3);
        Assert.assertEquals(14, max);
    }

    @Test
    public void test2() {
        int max = beers.maxBeers(3, 3);
        Assert.assertEquals(4, max);
    }

    @Test
    public void test3() {
        int max = beers.maxBeers(7, 3);
        Assert.assertEquals(10, max);
    }


}
