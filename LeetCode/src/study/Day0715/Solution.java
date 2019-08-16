package study.Day0715;

public class Solution {
    public boolean checkRecord(String s) {
        int Arecord = 0;
        int Lrecord = 0;
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'A') {
                Arecord++;
                if (Arecord > 1) {
                    return false;
                }
            }

            /**
             * 'L'的判断方法一
             */
            if (s.charAt(i) == 'L') {
                Lrecord++;
                if (Lrecord > 2) {
                    return false;
                }
            } else {
                // 因为题目要求是：不超过两个连续的'L'，所以当只有两个'L'并且下一个元素不是'L'的时候，将Lrecord清零
                Lrecord = 0;
            }

            /**
             * 'L'的判断方法二
             */
            if (s.contains("LLL")) {
                Lrecord++;
                if (Lrecord > 1) {
                    return false;
                }
            }

        }
        return true;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String s1 = "LLPPALLPLL";
        String s2 = "APPALLL";

        System.out.println(s.checkRecord(s1));
        System.out.println(s.checkRecord(s2));
    }
}
