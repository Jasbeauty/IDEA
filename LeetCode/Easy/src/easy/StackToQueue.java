package easy;

import java.util.Stack;

/**
 * 用两个栈（先进后出）实现队列（先进先出）
 */
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
