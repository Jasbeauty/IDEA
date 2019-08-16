package study.Day0620;

/**
 * 解题思路：
 * <p>
 * 1、首先找到链表中间节点以及它的前一个节点，可以利用快慢指针的思想，接着以中间节点构造二叉搜索树的根节点，划分其左右两边分别为左右子树
 * 2、然后断开根节点两边的链表，递归的构造子树，注意若子树只有两个节点，只需以首节点为根构造右子节点为其后节点的子树
 */
public class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        ListNode fast = head;
        ListNode slow = head;
        ListNode pre = head;
        if (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            pre = slow;
            slow = slow.next;
        }
        TreeNode root = new TreeNode(slow.val);
        if (slow != pre) {
            pre.next = null;
            root.left = sortedListToBST(head);
        }
        root.right = sortedListToBST(slow.next);
        return root;
    }
}

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
