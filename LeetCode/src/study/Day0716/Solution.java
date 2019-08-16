package study.Day0716;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        int staticSum = sum;
        Stack<TreeNode> nodeStack = new Stack<>();
        List<Integer> record = new LinkedList<>();
        List<List<Integer>> result = new LinkedList<>();

        while (root != null || !nodeStack.isEmpty()) {
            while (root != null) {
                nodeStack.push(root);
                record.add(root.val);
                sum -= root.val;
                root = root.left;
            }

            if (sum == 0) {
                result.add(record);
                sum = staticSum;

                // TODO...

            } else if (!nodeStack.isEmpty()) {
                root = nodeStack.pop();
                if (root.right == null) {
                    sum += root.val;
                    record.remove(record.size() - 1);
                    root = root.right;
                } else {
                    nodeStack.push(root.right);
                    root = root.right;
                }
            }
        }
        return result;
    }

    /**
     * 参考：回溯法
     */
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> pathSum1(TreeNode root, int sum) {
        List<Integer> list = new ArrayList<>();
        preOrder(root, list, sum);
        return result;
    }

    public void preOrder(TreeNode root, List<Integer> list, int sum) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        if (root.left == null && root.right == null && sum == root.val) {
            result.add(new ArrayList<>(list));
        }

        preOrder(root.left, list, sum - root.val);
        preOrder(root.right, list, sum - root.val);

        list.remove(list.size() - 1);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);

        List<List<Integer>> result = s.pathSum1(root, 22);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
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