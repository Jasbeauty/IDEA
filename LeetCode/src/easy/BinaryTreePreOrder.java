package easy;

public class BinaryTreePreOrder {
    // 二叉树的根节点
    Node root;

    BinaryTreePreOrder() {
        root = null;
    }

    public void printPreOrder(Node node) {
        if (node == null) {
            return;
        }

        System.out.print(node.key + " ");
        // 然后递归遍历左子树
        printPreOrder(node.left);
        // 然后递归遍历右子树
        printPreOrder(node.right);
    }

    public static void main(String[] args) {
        BinaryTreePreOrder treePreOrder = new BinaryTreePreOrder();
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        System.out.println("Preorder traversal of binary tree is: ");
        treePreOrder.printPreOrder(root);
    }
}

class Node {
    int key;
    Node left, right;

    public Node(int key) {
        this.key = key;
        left = right = null;
    }
}