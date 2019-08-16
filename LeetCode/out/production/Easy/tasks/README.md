### [实现LRU算法](#aa)
### [队列实现栈](#bb)
# <span id="aa">Basis</span>
#### [常用的页面置换算法（缓存算法）](#a)
#### [缓存](#b)
#### [java实现LRU](#c)
## <span id="a">页面置换算法（缓存算法）</span>

#### LRU
LRU（Least Recently Used）最近最少使用策略，无论是否过期，根据元素最后一次被使用的时间戳，清除最远使用时间戳的元素释放空间

主要比较元素最近一次被get使用时间。在热点数据场景下较适用，优先保证热点数据的有效性
#### FIFO
FIFO（First In First Out）先进先出策略，最先进入缓存的数据在缓存空间不够的情况下（超出最大元素限制）会被优先被清除掉，以腾出新的空间接受新的数据

主要比较缓存元素的创建时间。在数据实效性要求场景下可选择该类策略，优先保障最新数据可用
#### LFU
LFU（Less Frequently Used）最少使用策略，无论是否过期，根据元素的被使用次数判断，清除使用次数较少的元素释放空间

主要比较元素的hitCount（命中次数）。在保证高频数据有效性场景下，可选择这类策略




## <span id="b">缓存</span>
#### 什么是缓存
缓存Cache就是数据交换的缓冲区

其核心就是用空间换时间，通过分配一块高速存储区域（一般来说是内存）来提高数据的读写效率

根据缓存于应用的耦合度，可以分为local cache（本地缓存）和 remote cache（分布式缓存）
#### 缓存会不会过期
缓存每命中一次，就重新给该数据设置过期时间；那么经常命中的缓存始终不会过期，不会被删除，而非热点数据过期时间一到就会被删除掉
#### 缓存的原理是什么
每次需要数据的时候，先去缓存中（Map）查找，有的话就直接使用这个数据，没有的话就去数据库找或者创建一个满足条件的数据（构造一个实例）并把这个数据设置到缓存中，以备下次使用，这样避免了系统额外的开销
```java
/**
 * java 中缓存的基本实现
 */
public class JavaCache {
    // 一个本地的缓存变量
    private Map<String,Object> map = new HashMap<>();

    public void localCache(){
        List<Object> infoList = this.getInfoList();

        for (Object item :
                infoList) {
            if (map.containsKey(item)){     // 缓存命中，使用缓存数据
                // todo
            }else {     // 缓存未命中，IO获取数据，结果存入缓存
                Object valueObject = this.getInfoFromDB();
                map.put(valueObject.toString(),valueObject);
            }
        }
    }
    
    private List<Object> getInfoList(){
        return new ArrayList<>();
    }

    // 数据库IO获取
    private Object getInfoFromDB(){
        return new Object();
    }
}
```

## <span id="c">java实现LRU</span>
#### 核心思想
如果数据最近被访问过，那么将来被访问的几率也更高；所以将最近没有使用的数据从缓存中移除（淘汰的数据，是最后访问时间最早的）
> 可参考`LinkedHashMap`源码
#### 实现思路
数据存储的数据结构为链表，所以当访问数据时，如缓存中有数据，则将该数据移动至链表的顶端；没有该数据则在顶端加入该数据，并移除链表中的低端的数据
```
访问一条记录 -> 把链表head 指向当前记录 -> 当前记录中的next指向head之前指向的记录 -> 返回当前数据
```
> 每访问一次数据，都把最新的访问的数据放到了链表头部，那链表尾部的数据就是最近没有访问过的数据；当链表满了，从链表尾部开始往前删除指定数目的数据，就能在常数级（`O(1)`复杂度）时间内腾出空间

#### 基本实现
```java
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
        cache.get(1);  // return 1
        cache.put(3,3);
        cache.get(2);  // return -1
        cache.put(4,4);
        cache.get(1);  // return -1
        cache.get(3);  // return 3
        cache.get(4);  // return 4
    }
}
```

#### 源码解析
* LinkedList带boolean型参数的构造方法
```java
/**
 * Constructs an empty <tt>LinkedHashMap</tt> instance with the
 * specified initial capacity, load factor and ordering mode.
 *
 * @param  initialCapacity the initial capacity
 * @param  loadFactor      the load factor
 * @param  accessOrder     the ordering mode - <tt>true</tt> for
 *         access-order, <tt>false</tt> for insertion-order
 * @throws IllegalArgumentException if the initial capacity is negative
 *         or the load factor is nonpositive
 */
public LinkedHashMap(int initialCapacity,
                     float loadFactor,
                     boolean accessOrder) {
    super(initialCapacity, loadFactor);
    this.accessOrder = accessOrder;
}
```
> *  accessOrder为false时，表示所有的Entry按照插入的顺序排列，默认为false
> *  accessOrder为true时，表示所有的Entry按照访问的顺序排列

* LRU 容器核心
由于 LinkedHashMap 天生支持插入顺序或者访问顺序的 key-value 对，而 LRU 算法的核心恰巧用到它的访问顺序特性，即对一个键执行 get、put 操作后其对应的键值对会移到链表末尾，所以最末尾的是最近访问的，最开始的是最久没被访问的
```java
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 简单用LinkedHashMap来实现的LRU算法的缓存
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    // 最大缓存个数
    private int cacheSize;

    public LRUCache(int cacheSize) {
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
```
>  * 充分利用了 LinkedHashMap 的有序性特性和容量限制特性
>  * removeEldestEntry 这个方法如果返回true，则会移除最老的数据；这只会在调用 put 或者 putAll 时发生


# <span id="bb">Basis</span>
### [两个队列实现一个栈](#a)
### [一个队列实现一个栈](#b)
# <span id="a">两个队列实现一个栈</span>
### Logic
在`push`的时候，往非空的那个队列添加（初始化的时候，两个队列都为空，`offer`任意队列都行）

在`pop`的时候，如果队列1不为空，就把队列1中`q1.size()-1`个元素`poll`出来，添加到队列2中，再把队列中那个最后的元素`poll`出来

这两个队列始终保证有一个是空的，另一个非空。`push`添加元素到非空队列中，`pop`把非空队列中前面的元素都转移到另一个队列中，只剩最后一个元素，再把最后一个元素`poll`出来，这样这一个队列是空的，另一个队列又非空了

### Solution
```java
Queue<String> queue1 = new LinkedList<>();
Queue<String> queue2 = new LinkedList<>();

public void push(String args){
    if (!queue2.isEmpty()){
        queue2.offer(args);
    }
    else {
        queue1.offer(args);
    }
}

public String pop() {
    if (queue1.isEmpty()) {
        while (queue2.size() > 1) {
            queue1.offer(queue2.poll());
        }
        String x = queue2.poll();
        return x;
    } else {
        while (queue1.size() > 1) {
            queue2.offer(queue1.poll());
        }
        String x = queue1.poll();
        return x;
    }
}

public static void main(String[] args) {
    QueueToStack qts = new QueueToStack();
    qts.push("a");
    qts.push("b");
    qts.push("c");
    qts.push("test");
    System.out.println(qts.pop());
    System.out.println(qts.pop());

    System.out.println("=========== 新添加字符 ===========");

    qts.push("push");
    qts.push("push again");
    System.out.println(qts.pop());
    System.out.println(qts.pop());
    System.out.println(qts.pop());
    System.out.println(qts.pop());
}
```
输出
```
test
c
push again
```

# <span id="b">一个队列实现一个栈</span>
### Logic
对于`push`操作，栈与队列都是从队尾进行，直接`offer`就行

对于`pop`操作，假设队列长度为n（假设存储内容为头1,2,3尾），对从队头`poll`出来的元素执行`offer`存入队尾，循环进行`n-1`次操作（此时存储的内容为头3，1，2尾），然后再执行一次`poll`（取出3），即完成了栈的弹出

### Solution
```java
Queue<Integer> que = new LinkedList<>();

public void push1(Integer num){
    que.offer(num);
}

public Integer pop1(){
    if (que.isEmpty()){
        return null;
    }else {
        for (int i = 0; i < que.size()-1; i++) {
            que.offer(que.poll());
        }
        return que.poll();
    }
}

public static void main(String[] args) {
    QueueToStack qts = new QueueToStack();
    System.out.println(qts.pop1());
    qts.push1(1);
    qts.push1(3);
    qts.push1(5);
    System.out.println(qts.pop1());
    System.out.println("=========== 新添加数字 ===========");
    qts.push1(77);
    System.out.println(qts.pop1());
    System.out.println(qts.pop1());
    System.out.println(qts.pop1());
    System.out.println(qts.pop1());
}
```
输出
```
null
5
=========== 新添加数字 ===========
77
3
1
null
```
