package tasks;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class QueueToStack {
    Queue<String> queue1 = new LinkedList<>();
    Queue<String> queue2 = new LinkedList<>();

    Queue<Integer> que = new LinkedList<>();

    private List<Integer> list = new ArrayList<>();

    private int min = Integer.MAX_VALUE;

    public void push(String args) {
        if (!queue2.isEmpty()) {
            queue2.offer(args);
        } else {
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

    // 用一个队列实现栈操作
    public void push1(Integer num){
        if (num < min) {
            min = num;
        }
        list.add(min);

        que.offer(num);
    }

    public Integer pop1(){
        if (que.isEmpty()){
            return null;
        }else {
            int mMin = Integer.MAX_VALUE;
            for (int i = 0; i < que.size()-1; i++) {
                int tmp = que.poll();
                if (mMin > tmp) {
                    mMin = tmp;
                }
                que.offer(tmp);
            }
            min = mMin;
            list.remove(list.size() - 1);
            return que.poll();
        }
    }

    public int getMin() {
        return list.get(list.size() - 1);
    }

    public void print() {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();

    }


    public static void main(String[] args) {
        QueueToStack qts = new QueueToStack();
//        qts.push("a");
//        qts.push("b");
//        qts.push("c");
//        qts.push("test");
//        System.out.println(qts.pop());
//        System.out.println(qts.pop());
//
//        System.out.println("=========== 新添加字符 ===========");
//
//        qts.push("push");
//        qts.push("push again");
//        System.out.println(qts.pop());
//        System.out.println(qts.pop());
//        System.out.println(qts.pop());
//        System.out.println(qts.pop());

//        System.out.println(qts.pop1());
        qts.push1(1);
        qts.print();
        qts.push1(3);
        qts.print();
        qts.push1(5);
        qts.print();
        System.out.println(qts.pop1());
        System.out.println(qts.getMin());
        System.out.println("=========== 新添加数字 ===========");
        qts.push1(-1);
        qts.print();
        System.out.println(qts.getMin());

        System.out.println(qts.pop1());
        System.out.println(qts.pop1());
        System.out.println(qts.pop1());
        System.out.println(qts.pop1());
    }
}
