package study.Day0715;

public class Solution2 {
    int M = (int) 1e9 + 7;

    public int checkRecord(int n) {
        if (n == 0) {
            return 1;
        }
        /**
         * 对于某个长度为 i 的记录序列，以下变量表示对应情况的序列数量
         */

        /**
         * 对于长度为 1 的情况，记录的第一次是 P, L, A 均可奖励，都是只有 1 种
         */
        long P = 1;     // 序列中没有A，最新的一个记录不是L
        long AP = 0;    // 序列中有过A，最新的一个记录不是L
        long L = 1;     // 序列中没有A，最新的一个记录是L
        long LL = 0;    // 序列中没有A，最新的两个记录是LL
        long AL = 0;    // 序列中有过A，最新的一个记录是L
        long ALL = 0;   // 序列中有过A，最新的两个记录是LL
        long A = 1;     // 最新的一个记录是A

        /**
         * 等号左边计算长度为 i 的记录序列结果，右边引用长度为 i - 1 的记录序列结果
         * (必须写在一起，否则可能引用的是更新过后的值)
         */
        for (int i = 2; i <= n; i++) {
            long a = (P + L + LL) % M;            // 如果第i次记录是P
            long b = (AP + AL + ALL + A) % M;     // 如果第i次记录是P
            long c = P;                           // 如果第i次记录是L
            long d = L;                           // 如果第i次记录是L
            long e = (AP + A) % M;                // 如果第i次记录是L
            long f = AL;                          // 如果第i次记录是L
            long g = (P + L + LL) % M;            // 如果第i次记录是A

            P = a;
            AP = b;
            L = c;
            LL = d;
            AL = e;
            ALL = f;
            A = g;
        }
        return (int) (P + AP + L + LL + AL + ALL + A) % M;
    }

    public static void main(String[] args) {
        Solution2 s = new Solution2();
        System.out.println(s.checkRecord(4));
        char myChar = 'g';
        String myStr = Character.toString(myChar);
        System.out.println("String is: "+myStr);
        myStr = String.valueOf(myChar);
        System.out.println("String is: "+myStr);
    }
}




