package cn.bj.brook.algorithm;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class TestAssignCandies {

    private AssignCandies candy;

    private int[] rs;

    private int[] rs2;

    public TestAssignCandies() {
        candy = new AssignCandies();
    }

    @Before
    public void init(){

    }

    @After
    public void tearDown(){
        System.out.println(Arrays.toString(rs));
    }

    @Test
    public void test1() {
        int candies = 220;
        int number = 5;
        rs = candy.distributeCandies(candies, number);
        Assert.assertEquals(220, Arrays.stream(rs).sum());
    }

    @Test
    public void test2() {
        int candies = 100;
        int number = 6;
        rs = candy.distributeCandies(candies, number);
        Assert.assertEquals(100, Arrays.stream(rs).sum());
    }

    @Test
    public void test3() {
        int candies = 10;
        int number = 3;
        rs = candy.distributeCandies(candies, number);
        Assert.assertEquals(10, Arrays.stream(rs).sum());
    }

    @Test
    public void test4() {
        int candies = 1000000000;
        int number = 1000;
        rs = candy.distributeCandies(candies, number);
        Assert.assertEquals(1000000000, Arrays.stream(rs).sum());
    }

}
