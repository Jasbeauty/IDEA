package tasks;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {
    private LinkedHashMap<Integer,Integer> map;
    private final int CAPACITY;

    public LRUCache(int capacity) {
        CAPACITY = capacity;
        map = new LinkedHashMap<Integer, Integer>(capacity,0.75f,true){
            protected boolean removeEntry(Map.Entry eldest){
                return size()>CAPACITY;
            }
        };
    }

    public int get(int key){
        return map.getOrDefault(key,-1);
    }

    public void put(int key,int value){
        map.put(key,value);
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put(1,1);
        cache.put(2,2);
        cache.get(1);
        cache.put(3,3);
        cache.get(2);
        cache.put(4,4);
        cache.get(1);
        cache.get(3);
        cache.get(4);
    }
}
