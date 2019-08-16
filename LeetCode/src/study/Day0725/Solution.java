package study.Day0725;

public class Solution {
    /**
     * 自顶向下
     */
    public boolean isBalanced(TreeNode root) {
        while (root != null) {
            int leftLen = getLength(root.left);
            int rightLen = getLength(root.right);
            int result = Math.abs(leftLen - rightLen);
            return isBalanced(root.left) && isBalanced(root.right) && result <= 1;
        }
        return true;
    }

    // 获取树的深度
    public static int getLength(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int depth1;
            int depth2;
            depth1 = getLength(root.left);
            depth2 = getLength(root.right);
            if (depth1 > depth2) {
                return depth1 + 1;
            } else {
                return depth2 + 1;
            }
        }
    }

    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(depth(root.left), depth(root.right)) + 1;
        }
    }

    /**
     * 自底向上
     */
    boolean res = true;

    public boolean isBalanced1(TreeNode root) {

        helper(root);
        return res;

    }

    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left) + 1;
        int right = helper(root.right) + 1;
        if (Math.abs(right - left) > 1) {
            res = false;
        }
        return Math.max(left, right);
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(5);
        System.out.println(s.isBalanced1(root));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}