package study.Day0809;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * 数据结构的设计思路：
 * （1）HashMap<K, V>
 * Since we need to find the least frequently used item to remove it for putting new data, we need a counter to keep track number of times a Key(K) has been accessed.
 * Access could get or put.
 * To achieve that we need another Map<K, C>; K is the key of the item to put and C is the counter.
 * （2）HashMap<K, C>
 * （3）HashMap<K,LinkedHashSet<K>>
 */
public class LFUCache {
    /**
     * From the below two data structure, we can put and get data in O(1).
     * We can also get the counter of an item has been used.
     */
    HashMap<Integer, Integer> vals;     //cache K and V
    HashMap<Integer, Integer> counts;   //K and counters

    /**
     * Another thing, we need, is a list where we can store the information of count and items key.
     * Lets elaborate that in details, assume A has been used 5times, B also has been used 5times.
     * We need to store that information such a way that will hold the items in a list based on their insertion order. (FIFO).
     * To achieve that we can use HashSet<K> and more precisely LinkedHashSet<K>.
     * But we want to keep track of the counter as well(in our example 5 times or 5).
     * So we need another map as below.
     */
    HashMap<Integer, LinkedHashSet<Integer>> lists;     //Counter and item list

    // 缓存容量
    int cap;

    /**
     * We need a tag or min variable, it will hold the current min.
     * Whenever a new Item inserts into the cache min=1;
     * It will be increased only when there are no items in the (counter==min).
     */
    int min = -1;

    public LFUCache(int capacity) {
        cap = capacity;
        vals = new HashMap<>();
        counts = new HashMap<>();
        lists = new HashMap<>();
        lists.put(1, new LinkedHashSet<>());
    }

    public int get(int key) {
        if (!vals.containsKey(key))
            return -1;
        // Get the count from counts map
        int count = counts.get(key);
        // increase the counter
        counts.put(key, count + 1);
        // remove the element from the counter to linkedhashset
        lists.get(count).remove(key);

        // when current min does not have any data, next one would be the min
        if (count == min && lists.get(count).size() == 0)
            min++;
        if (!lists.containsKey(count + 1))
            lists.put(count + 1, new LinkedHashSet<>());
        lists.get(count + 1).add(key);
        return vals.get(key);
    }

    public void put(int key, int value) {
        if (cap <= 0)
            return;
        // If key does exist, we are returning from here
        if (vals.containsKey(key)) {
            vals.put(key, value);
            get(key);
            return;
        }
        if (vals.size() >= cap) {
            int evit = lists.get(min).iterator().next();
            lists.get(min).remove(evit);
            vals.remove(evit);
            counts.remove(evit);
        }
        // If the key is new, insert the value and current min should be 1 of course
        vals.put(key, value);
        counts.put(key, 1);
        min = 1;
        lists.get(1).add(key);
    }


    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2 /* capacity (缓存容量) */);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回 1
        cache.put(3, 3);    // 去除 key 2
        System.out.println(cache.get(2));       // 返回 -1 (未找到key 2)
        System.out.println(cache.get(3));       // 返回 3
        cache.put(4, 4);    // 去除 key 1
        System.out.println(cache.get(1));       // 返回 -1 (未找到 key 1)
        System.out.println(cache.get(3));       // 返回 3
        System.out.println(cache.get(4));       // 返回 4
    }
}
