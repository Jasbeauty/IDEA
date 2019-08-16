package study.Day0723;

import java.util.*;

public class Solution {
    /**
     * 类似自己的思路：父指针迭代
     * 时间复杂度：O(n)，空间复杂度：O(n)
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //  定义一个栈，用来存放根节点root
        Stack<TreeNode> stack = new Stack<>();
        // 定义一个map用来存储，key为子节点，value为其对应的最近父节点
        Map<TreeNode, TreeNode> parent = new HashMap<>();

        // 初始化 stack、map
        stack.push(root);
        parent.put(root, null);

        // 当输入的p,q都不是根节点root，完善map
        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            // 获取题中给定的根节点
            TreeNode node = stack.pop();

            // 列出所有key为子节点，value为其对应的最近父节点
            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }

            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }

        // 用来存放其中一个节点的所有父节点，包括它自己
        List<TreeNode> list = new ArrayList<>();

        // 列出 p 所有父节的list，包括它自己（因为代码实现中，是先add再找上一级父节点）
        while (p != null) {
            list.add(p);
            p = parent.get(p);
        }

        // 如果list中不包含 q ，就找其上一级节点在不在list中，直至找到就返回
        while (!list.contains(q)) {
            q = parent.get(q);
        }
        return q;
    }


    /**
     * 回溯法 DFS
     * 时间复杂度：O(n)，空间复杂度：O(n)
     */

    private TreeNode ans;

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return ans;
    }

    private boolean dfs(TreeNode cureentNode, TreeNode p, TreeNode q) {
        // 如果到了分支最底部，返回false
        if (cureentNode == null) {
            return false;
        }

        int left = dfs(cureentNode.left, p, q) ? 1 : 0;
        int right = dfs(cureentNode.right, p, q) ? 1 : 0;

        int mid = (cureentNode == p || cureentNode == q) ? 1 : 0;

        if (mid + left + right >= 2) {
            this.ans = cureentNode;
        }
        return (mid + left + right > 0);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        System.out.println(s.lowestCommonAncestor1(root, root.left, root.right.right).val);
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