package study.BackTrackingMethod;

import java.util.Scanner;

/**
 * 对于一个与 n 级台阶组成的楼梯，爬楼梯时一次可以上 1 级台阶或 2 级台阶
 * 求解共有多少种不同的走法
 */
public class ClimbStairs {
    public static int s = 1;
    public static int[] steps = new int[10];

    public static void main(String[] args) {
        System.out.println("请输入台阶数：");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        tryStep(n);
    }

    public static void tryStep(int n) {
        for (int i = 0; i <= 2; i++) {
            // 对于每次爬有两次尝试，一次爬1级或者一次爬2级
            if (n < i) {
                break;
            }
            steps[s++] = i; // 一步走了i级台阶
            n -= i; //缩小问题的规模
            if (n == 0) {
                for (int j = 1; j < s; j++) {
                    System.out.print("第" + j + "步走了" + steps[j]
                            + "级台阶 ");
                }
                System.out.println();
            } else {
                tryStep(n);
            }
            n += i;
            steps[s--] = 0;
        }
    }
}
