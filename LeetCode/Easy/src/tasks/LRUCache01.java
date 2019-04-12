package tasks;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 简单用LinkedHashMap来实现的LRU算法的缓存
 */
public class LRUCache01<K, V> extends LinkedHashMap<K, V> {
    // 最大缓存个数
    private int cacheSize;

    public LRUCache01(int cacheSize) {
        super(16, (float) 0.75, true);
        this.cacheSize = cacheSize;
    }

    // 在添加元素到LinkedHashMap后会调用这个方法，传递的参数是最久没被访问的键值对
    // 如果这个方法返回true则这个最久的键值对就会被删除，LinkedHashMap的实现总是返回false
    // 所以容量没有限制
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > cacheSize;
    }
}

