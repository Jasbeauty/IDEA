package study.Day0612;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Solution {

    /**
     * 二叉搜索树BST：
     * 1. 从微观上来讲，BST的每个节点都大于其左节点，且小于其右节点。
     * 2. 从宏观上来将，BST的每个节点都大于其左子树的每个节点，且小于其右子树的每个节点。
     * 3. 一棵BST的中序遍历是有序的，这个性质我们称为BST的单调性
     * <p>
     * 分治法和动态规划
     * 1. 相同点：都是将原问题分而治之,分解成若干个规模较小
     * 2. 区别：分治法常常利用递归求解，分解后的子问题看成相互独立的；动态规划通常利用迭代自底向上求解，或者具有记忆能力对桂法自顶向下，其分解的子问题理解成相互有联系的
     * <p>
     * 本题解题思路：
     * 1. 选出根结点后应该先分别求解该根的左右子树集合，也就是根的左子树有若干种，它们组成左子树集合，根的右子树有若干种，它们组成右子树集合
     * 2. 然后将左右子树相互配对，每一个左子树都与所有右子树匹配，每一个右子树都与所有的左子树匹配。然后将两个子树插在根结点上
     * 3. 最后，把根结点放入list中
     */

    public List<TreeNode> generateTrees(int n) {
        if (n < 1) {
            return new ArrayList<>();
        }
        return generate(1, n);
    }

    public List<TreeNode> generate(int start, int end) {
        List<TreeNode> list = new ArrayList<>();
        // 加null至关重要
        if (start > end) {
            list.add(null);
            return list;
        }

        // 确定根节点
        for (int i = start; i <= end; i++) {
            // 确定左右子树
            List<TreeNode> left = generate(start, i - 1);
            List<TreeNode> right = generate(i + 1, end);
            for (TreeNode leftT : left) {
                for (TreeNode rightT : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftT;
                    root.right = rightT;
                    list.add(root);
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        List<TreeNode> result = s.generateTrees(3);
        for (int i = 0; i < result.size(); i++) {
            s.tree(result.get(i));
            System.out.println();
        }
    }

    public void tree(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        System.out.print(treeNode.val + " ");
        tree(treeNode.left);
        tree(treeNode.right);
    }
}
