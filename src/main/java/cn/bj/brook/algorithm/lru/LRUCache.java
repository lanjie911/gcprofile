package cn.bj.brook.algorithm.lru;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {

    Map<Integer,Integer> cache = null;

    public LRUCache(int capacity) {
        cache = new LinkedHashMap<>(capacity,0.75f,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                if(this.size() > capacity){
                    return true;
                }
                return false;
            }
        };
    }

    public int get(int key) {
        Integer v = this.cache.get(key);
        return v==null?-1:v.intValue();
    }

    public void put(int key, int value) {
        this.cache.put(key,value);
    }
}
