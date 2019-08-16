package study.Day0619;

public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head;
        if (l1.val > l2.val) {
            head = l2;
            head.next = mergeTwoLists(l1, l2.next);
        } else {
            head = l1;
            head.next = mergeTwoLists(l1.next, l2);
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(3);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(7);

        Solution s = new Solution();
        ListNode result = s.mergeTwoLists(l1, l2);
        while (result != null) {
            System.out.print(result.val + " --> ");
            result = result.next;
        }
    }
}

class ListNode {
    // 定义val变量值，存储节点值
    int val;
    // 定义next指针，指向下一个节点，维持节点连接
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
