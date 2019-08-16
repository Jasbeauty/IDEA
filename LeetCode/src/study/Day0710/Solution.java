package study.Day0710;

public class Solution {
    public void reverseString(char[] s) {
        int len = s.length;
        if (len != 0) {
            for (int i = 0; i < s.length / 2; i++) {
                char temp = s[i];
                s[i] = s[len - 1];
                s[len - 1] = temp;
                len--;
            }
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        char[] test = new char[]{'a', 'p', 'p', 'l', 'e'};
        char[] test1 = new char[]{'l', 'o', 'v', 'e'};
        char[] test2 = new char[]{};

        System.out.print("反转前：");
        System.out.println(test);
        s.reverseString(test);
        System.out.print("反转后：");
        System.out.println(test);

        System.out.print("反转前：");
        System.out.println(test1);
        s.reverseString(test1);
        System.out.print("反转后：");
        System.out.println(test1);

        System.out.print("反转前：");
        System.out.println(test2);
        s.reverseString(test2);
        System.out.print("反转后：");
        System.out.println(test2);
    }
}
