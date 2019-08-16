package study.BackTrackingMethod;

/**
 * 约瑟芬杀人问题：
 * 让俘虏围成一圈，从 1 开始数数
 * 数到某个数时，杀掉一个人，重新开始从 1 数数，直到剩下一个人
 */
public class Josephus {
    private static int N = 20;
    // 数到 M 就把人kill
    private static int M = 5;

    class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public void killNode() {
        // 第一个节点
        Node header = new Node(1);
        // 目标被点到的人
        Node x = header;
        // 记录kill次数
        int count = 0;

        // 定义出20个俘虏
        for (int i = 2; i <= N; i++) {
            x = (x.next = new Node(i));
        }

        // 此时x为最后一个节点，让它的next成为第一个节点，使得这些节点都连起来了，形成一个环
        x.next = header;

        System.out.println("被 kill 的顺序：");

        // 至少还有两个人时，仍然继续报数
        while (x != x.next) {
            for (int i = 1; i < M; i++) {
                // 每次数到 4 的时候，就跳出for循环
                x = x.next;
            }

            count++;
            // 应当kill当前节点的下一个节点
            System.out.println("第 " + count + " 次 kill，" + x.next.val + " was killed");
            // 让当前节点的next成为它的next的next即可
            x.next = x.next.next;
        }

        System.out.println("The lucky guy is " + x.val);
    }

    public static void main(String[] args) {
        Josephus josephus = new Josephus();
        josephus.killNode();
    }
}
