package cn.bj.brook.algorithm.tree.trie;

import org.junit.Assert;
import org.junit.Test;

public class TestTire {
    private Tire t;

    public TestTire() {

    }

    @Test
    public void test1() {
        t = new Tire();
        t.insert("hello");
        t.insert("hex");
        boolean b = t.search("hello");
        Assert.assertEquals(true, b);
    }

    @Test
    public void test2() {
        t = new Tire();
        t.insert("hello");
        t.insert("hex");
        boolean b = t.search("hel");
        Assert.assertEquals(false, b);
    }

    @Test
    public void test3() {
        t = new Tire();
        t.insert("hello");
        t.insert("hex");
        boolean b = t.startsWith("hel");
        Assert.assertEquals(true, b);
    }
}
