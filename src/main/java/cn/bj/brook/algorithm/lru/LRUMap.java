package cn.bj.brook.algorithm.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 使用LinkedHashMap来实现一个LRU缓存
 * 通过覆盖removeEldestEntry来决定如何删除最老的缓存
 * 这个Map是自动计算访问次数的
 * @param <K>
 * @param <V>
 */
public class LRUMap<K,V> extends LinkedHashMap{
    LRUMap(int a, float b, boolean c){
        super(a,b,c);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        if(this.size() >= 5){
            System.out.println(eldest.getKey()+" removed...");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        // 必须按照访问顺序来排列元素
        // 为false的话就是按照插入的顺序来排列了
        Map<String,Integer> m = new LRUMap<String,Integer>(20,0.75f,true);
        m.put("1",100);
        m.put("2",200);
        m.get("1");
        m.put("3",333);
        m.put("4",444);
        m.put("5",555);
        m.put("6",666);
        // 1被踢出去了，所以会显示null
        System.out.println(m.get("1"));
    }
}
