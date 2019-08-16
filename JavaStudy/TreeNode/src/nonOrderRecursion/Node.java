package nonOrderRecursion;

import java.util.Stack;

/**
 * 二叉树
 * 前序、中序 非递归遍历
 */
public class Node {
    // 对应节点上的值
    String val;
    // 左子树
    public Node leftChild;
    // 右子树
    public Node rightChild;

    public Node(String x) {
        val = x;
    }

    @Override
    public String toString() {
        return "val: " + val;
    }

    // 访问节点
    public static void visit(Node node) {
        System.out.print(node.val + " ");
    }

    // 前序遍历
    public static void preOrderTraversal(Node indexNode) {
        // 用来暂存节点的栈
        Stack<Node> nodeStack = new Stack<>();

        while (indexNode != null || !nodeStack.isEmpty()) {

            // 若当前考查节点非空，则输出该节点的值
            // 因为是前序遍历，需要一直往左走
            while (indexNode != null) {
                visit(indexNode);
                // 为了之后能找到该节点的右子树，暂存该节点
                nodeStack.push(indexNode);
                indexNode = indexNode.leftChild;
            }

            // 一直到左子树为空，则开始考虑右子树
            // 如果栈已空，就不需要再考虑
            // 弹出栈顶元素，将游标等于该节点的右子树
            if (!nodeStack.isEmpty()) {
                indexNode = nodeStack.pop();
                indexNode = indexNode.rightChild;
            }
        }
    }


    // 中序遍历
    public static void inOrderTraversal(Node indexNode) {
        // 用来暂存节点的栈
        Stack<Node> nodeStack = new Stack<>();
        while (indexNode != null || !nodeStack.isEmpty()) {
            while (indexNode != null) {
                nodeStack.push(indexNode);
                indexNode = indexNode.leftChild;
            }
            if (!nodeStack.isEmpty()) {
                indexNode = nodeStack.pop();
                visit(indexNode);
                indexNode = indexNode.rightChild;
            }
        }
    }

    // 后序遍历
    public static void postOrderTraversal(Node indexNode) {
        // 用来暂存节点的栈
        Stack<Node> nodeStack = new Stack<>();
        Node lastVisit = indexNode;
        while (indexNode != null || !nodeStack.isEmpty()) {
            while (indexNode != null) {
                nodeStack.push(indexNode);
                indexNode = indexNode.leftChild;
            }
            // 查看当前栈顶元素
            indexNode = nodeStack.peek();

            //如果其右子树也为空，或者右子树已经访问，则可以直接输出当前节点的值
            if (indexNode.rightChild == null || indexNode.rightChild == lastVisit) {
                visit(indexNode);
                nodeStack.pop();
                lastVisit = indexNode;
                indexNode = null;
            } else {
                // 否则继续遍历右子树
                indexNode = indexNode.rightChild;
            }
        }
    }


    // 后序 非递归遍历（使用两个栈）

    /**
     * 具体思路：
     * 定义两个栈 S1和S2
     * 首先根节点入栈S1，当S1不为空弹出栈顶元素，并入栈S2，如果说弹出的左右子树不为空，则依次将其左子树和右子树入栈。直到栈S1为空，才停止
     * 然后输出栈S2，即为后续遍历的结果
     *
     * @param indexNode
     */

// 从遍历的特点可以看出来，最先访问的节点最后遍历，因此符合栈的特点
    public static void postOrderTraversal2(Node indexNode) {
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        Node temp = indexNode;

        while (temp != null || !s1.isEmpty()) {
            // 每次从根节点开始，一直向右下方向搜寻直到空，逐个入栈
            while (temp != null) {
                s1.push(temp);
                s2.push(temp);
                temp = temp.rightChild;
            }
            // 遇到 null后，依次出栈
            if (!s1.isEmpty()) {
                // 让出栈点的左子树执行相同的操作
                temp = s1.pop();
                temp = temp.leftChild;
            }
        }

        // 依次输出栈s2的值，即后序遍历的结果
        while (!s2.isEmpty()) {
            temp = s2.pop();
            visit(temp);
        }

    }

    public static void main(String[] args) {
        Node root = new Node("a");
        root.leftChild = new Node("b");
        root.rightChild = new Node("c");
        root.leftChild.leftChild = new Node("d");
        root.leftChild.rightChild = new Node("f");
        root.leftChild.leftChild.rightChild = new Node("e");
        root.leftChild.rightChild.leftChild = new Node("g");

        // 前序
//        preOrderTraversal(root);
//        System.out.println();
//        // 中序
//        inOrderTraversal(root);
//        System.out.println();
        // 后序
        postOrderTraversal(root);
        System.out.println();
        postOrderTraversal2(root);
    }
}
