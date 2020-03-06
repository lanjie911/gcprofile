package cn.bj.brook.algorithm.lru;

import org.junit.Assert;
import org.junit.Test;

public class TestLRUCache {
    private LRUCache cache;

    public void TestLRUCache() {

    }

    @Test
    public void test1() {
        cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        int m = cache.get(1);
        Assert.assertEquals(1, m);
        cache.put(3, 3);
        m = cache.get(2);
        Assert.assertEquals(-1, m);
        cache.put(4, 4);
        m = cache.get(1);
        Assert.assertEquals(-1, m);
        m = cache.get(3);
        Assert.assertEquals(3, m);
        m = cache.get(4);
        Assert.assertEquals(4, m);

    }
}
