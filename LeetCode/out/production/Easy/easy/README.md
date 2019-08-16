### [用两个栈（先进后出）实现队列（先进先出）](#aa)
### [List 接口](#bb)
### [数组实现ArrayList、LinkedList](#cc)

# <span id="aa">用两个栈（先进后出）实现队列（先进先出）</span>
```java
public class StackToQueue {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    public void push(int node){
        stack1.push(node);
    }
    public int pop(){
        if (stack2.isEmpty()){
            while (!stack1.isEmpty()){  // 每次stack2取完之后，将所有stack1中所有的元素放到stack2中顺序翻转
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public static void main(String[] args) {
        StackToQueue que = new StackToQueue();
        que.push(5);
        que.push(2);
        que.push(3);
        System.out.println(que.pop());
        System.out.println(que.pop());
        System.out.println(que.pop());
    }
}

```
输出
```
5
2
3
```

# <span id="bb">List 接口</span>
### List 是一组有序的对象集合

### 常用方法
* get()：通过索引查询元素
* set()：通过索引设定元素`set(0,args)`
* add()：既可以把元素添加到列表末尾`add(args)`，也可以把元素插入指定索引`add(1,args)`
* remove()：从列表中删除元素
* size()：返回列表长度
* subList()：返回一个List对象，表示原列表指定范围内的元素（`subList(1,3)`是返回原列表的第二个和第三个元素）

### 创建列表
```java
List<String> l = new ArrayList<String>(Arrays.asList(args))
```

### Iterator遍历循环和迭代
```java
// 使用while循环迭代集合中的元素
// 有些集合种类（例如列表）能保障迭代的顺序，有些则不能
ListCollection<String> c = new ArrayList<String>()
Iterator<String> iterator = c.iterator();
while(iterator.hasNext()){
    System.out.println(iterator.next())
}
```
* Iterator接口定义了一种迭代集合或其他数据结构中元素的方式
* 迭代的过程：只要集合中还有更多的元素（hasNext()方法返回true），就调用`next()`方法获取集合中的下一个元素
* 有序集合（例如列表）的迭代器一般能保证按照顺序返回元素
* Iterator接口中next()的作用：把集合的游标向前移动，然后返回集合的前一个头值
* 如果使用遍历循环迭代Iterator<E>对象，循环变量必须是E类型，或者是E类型的超类或实现的接口

### 深拷贝 & 浅拷贝
##### 深拷贝
将A复制给B的同时，给B创建新的地址，再将地址A的内容传递到地址B。ListA与ListB内容一致，但是由于所指向的地址不同，所以改变相互不受影响
##### 浅拷贝
在拷贝这个对象的时候，只对引用数据类型进行了引用的传递，而没有真实的创建一个新的对象，两者仍指向同一个地址，则认为是浅拷贝，即内存地址引用；所以改变新的引用时，原来引用的值也会改变

### 实例 
将List[1,2,3,3,4,5]变成[1,2,4,5]
```java
List<Integer> a = new ArrayList<>(Arrays.asList(1,2,3,3,4,5));

// 方法一
for (int i = 0; i < a.size(); i++) {
            if (a.get(i)==3){
                a.remove(i);
                i--;    // 使得可以把满足条件需要删除的元素完全删掉
            }
        }

System.out.println(a.size());   // 输出：4
System.out.println(a);  // 输出：[1, 2, 4, 5]

//方法二
// 优先使用迭代器遍历
Iterator<Integer> integerIterator = a.iterator();
while (integerIterator.hasNext()) {
    int val = integerIterator.next();
    if (val == 3) {
        integerIterator.remove();
    }
}

System.out.println(a.size());   //输出：4
System.out.println(a);  // 输出：[1, 2, 4, 5]

// 方法三
// 不建议使用以下方法
for(Integer s : a){
    if (s==3){
        a.remove(s);
        break;  // 使用break或者return语句结束了循环操作，不报ConcurrentModificationException异常
    }
}

System.out.println(a.size());   // 输出：5
System.out.println(a);  // 输出：[1, 2, 3, 4, 5]
```




# <span id="cc">数组实现ArrayList、LinkedList</span>
### 数组实现 ArrayList
```java
//    数组实现ArrayList的add()、get()、remove()、contains()方法
    static class ArrayList {
//        seq表示数组下标
        private int seq = 0;

        private int[] arrs = new int[2];

        //扩容
        //Arrays.copyRange()
        public void add(int val) {
            if (seq >= arrs.length - 1) {
                //说明数组满了，需要扩容
                int[] news = new int[arrs.length * 2];
                extend(arrs, news);
            }
            arrs[seq ++] = val;
        }

        public int get(int index) {
            if(index < seq){
                return arrs[index];
            }
            return 0;
        }

        public void remove(int index) {
            int[] removed = new int[arrs.length-1];
            if (index > 0){
                System.arraycopy(arrs,0,removed,0,index);
                System.arraycopy(arrs,index+1,removed,index,arrs.length-index-1);
                seq --;
            }
//            removed = null;
            arrs = removed;
        }

        public boolean contains(int val) {
            int position = Arrays.binarySearch(arrs,val);
            return position >= 0;
        }

        /**
         * 将olds数组里面的值复制到news里面，同时重新将news结果复值到olds
         * @param olds
         * @param news
         */
        private void extend(int[] olds, int[] news) {
            System.arraycopy(olds,0,news,0,olds.length);
//            System.out.println(olds.length);
//            System.out.println(news.length);
            arrs = news;
        }

        public int size() {
            return seq;
        }
    }

```

### 数组实现 LinkedList
```java
public class LinkedList {

    static class Node {
        // 前一个节点
        private Node pre;
        // 后一个节点
        private Node next;
        // 节点的值
        private int val;

        Node(int val) {
            this.val = val;
        }
    }

    private static Node node;
    // 头节点
    private Node head;

    private static int seq = 0;

    public static void main(String[] args) {
        LinkedList l = new LinkedList();
        Node node = new Node(1);
        node.val = 1;
        for (int i = 0; i < 3; i++) {
            l.add(i);
        }
        System.out.println(l.size());
        l.remove(0);
        System.out.println(l.contains(3));
        for (int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i));
        }

        System.out.println(l.size());


    }

    public void add(int val) {
        if (seq == 0) {
            node = new Node(val);
            head = node;
        } else {
            node.next = new Node(val);
            node = node.next;
        }
        seq ++;
    }


    public void remove(int index) {
        if (index > seq - 1) {
          return;
        } else if (index == 0){
            head = head.next;
        } else {
            Node head1 = head;
            Node preNode = head;
            for (int i = 0; i < index; i++) {
                preNode = head1;
                head1 = head1.next;
            }
            if (head1 == node) {
                node = preNode;
                preNode.next = null;
                head1 = null;
            } else {
                preNode.next = head1.next;
                head1.next = null;
            }
        }
        seq--;
    }

    public boolean contains(int val) {
        Node head1 = head;
        while (head1 != null) {
            if (head1.val == val) {
                return true;
            }
            head1 = head1.next;

        }
        return false;
    }

    public int size() {
        return seq;
    }

    public int get(int index) {
        if (index<seq){
            Node head1 = head;
            for (int i = 0; i < index; i++) {
                head1 = head1.next;
            }
            int val = head1.val;
            head1 = null;
            return val;
        }
        return 0;
    }

}

```
