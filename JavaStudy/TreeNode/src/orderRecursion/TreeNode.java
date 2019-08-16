package orderRecursion;

/**
 * 二叉树
 * 前序、中序、后序递归遍历
 */
public class TreeNode {

    // 对应节点上的值
    int val;
    // 左子树
    public TreeNode leftChild;
    // 右子树
    public TreeNode rightChild;

    public TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "val: " + val;
    }

    // 访问节点
    public static void visit(TreeNode node) {
        System.out.print(node.val + " ");
    }

    // 前序递归遍历（先输出节点的值，再递归遍历左右子树）
    public static void preOrderRecursion(TreeNode node) {
        if (node != null) {
            visit(node);
            preOrderRecursion(node.leftChild);
            preOrderRecursion(node.rightChild);
        }
    }

    // 中序递归遍历（先访问左节点，再访问根节点，最后访问右节点）
    public static void inOrderRecursion(TreeNode node) {
        if (node != null) {
            preOrderRecursion(node.leftChild);
            visit(node);
            preOrderRecursion(node.rightChild);
        }
    }

    // 后序递归遍历（先访问左节点，再访问右节点，最后访问根节点）
    public static void postOrderRecursion(TreeNode node) {
        if (node != null) {
            preOrderRecursion(node.leftChild);
            preOrderRecursion(node.rightChild);
            visit(node);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.leftChild = new TreeNode(2);
        root.rightChild = new TreeNode(3);
        root.leftChild.leftChild = new TreeNode(4);
        root.leftChild.rightChild = new TreeNode(5);


        // 前序递归
        preOrderRecursion(root);
        System.out.println();
        // 中序递归
        inOrderRecursion(root);
        System.out.println();
        // 后序递归
        postOrderRecursion(root);
    }


}
