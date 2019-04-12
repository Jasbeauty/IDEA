package easy;

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
