package study.Recursion;

public class Fib {
    // 斐波那契数列为：0,1,1,2,3,5...
    public int fib(int n) {
        if (0 == n) {
            return 0;
        }
        if (1 == n) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        Fib fib = new Fib();
        System.out.println(fib.fib(4));
    }
}
