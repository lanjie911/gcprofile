package cn.bj.brook.algorithm.array;

import cn.bj.brook.algorithm.bitoper.ReverseBinaryBitSequence;
import org.junit.Assert;
import org.junit.Test;

public class TestReverseBinaryBitSequence {
    private ReverseBinaryBitSequence b;

    public TestReverseBinaryBitSequence(){
        b = new ReverseBinaryBitSequence();
    }

    @Test
    public void test1(){
        int n = 0b00000010100101000001111010011100;
        int t = b.reverseBinaryExpression(n);
        System.out.println(Integer.toBinaryString(t));
        Assert.assertEquals(0b00111001011110000010100101000000,t);
    }

    @Test
    public void test2(){
        int n = 0b00000010_11111111_00011110_10011100;
        int t = b.reverseBinaryExpression(n);
        System.out.println(Integer.toBinaryString(t));
        Assert.assertEquals(0b00111001_01111000_11111111_01000000,t);
    }

    @Test
    public void test3(){
        int n = 0b11111111111111111111111111111101;
        int t = b.reverseBinaryExpression(n);
        Assert.assertEquals(0b10111111111111111111111111111111,t);
    }
}
