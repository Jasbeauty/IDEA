package easy;

public class HashMap {
    Node[] arrs = new Node[16];
}

class Node {
    Node next;
    int val;
    Node(int val) {
        this.val = val;
    }
}