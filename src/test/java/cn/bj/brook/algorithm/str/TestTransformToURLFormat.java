package cn.bj.brook.algorithm.str;

import org.junit.Assert;
import org.junit.Test;

public class TestTransformToURLFormat {
    private TransformToURLFormat format;

    public TestTransformToURLFormat() {
        format = new TransformToURLFormat();
    }

    @Test
    public void test1(){
        String t = "Mr John Smith    ";
        int p = 13;
        String s = format.replaceSpaces(t,p);
        Assert.assertEquals("Mr%20John%20Smith",s);
    }

    @Test
    public void test2(){
        String t = "               ";
        int p = 5;
        String s = format.replaceSpaces(t,p);
        Assert.assertEquals("%20%20%20%20%20",s);
    }
}
