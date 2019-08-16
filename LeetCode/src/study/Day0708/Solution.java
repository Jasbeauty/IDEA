package study.Day0708;


/**
 * KMP 算法（一种改进的字符串匹配算法）
 * 关键：利用匹配失败后的信息，来尽量减少模式串与主串的匹配次数，以达到快速匹配的目的
 * 具体实现：实现一个next()函数
 * 时间复杂度：O(m+n)
 */

/**
 * 解题思路：
 * 1、设置两个指针i和j，分别用于指向主串和模式串
 * 2、从左到右开始一个个匹配
 * 3、如果主串和模式串的俩字符相等，则i和j同时后移一位，比较下一个字符
 * 4、如果主串和模式串的俩字符不相等，就跳回去，也就是主串右移一位，同时模式串归零
 * 5、所有字符串匹配结束后，如果模式串指针指到串尾（j==needArray.length），说明完全匹配，此时字符串出现的第一个位置为：i-j
 */
public class Solution {
    public int strStr(String haystack, String needle) {
        char[] haystackArray = haystack.toCharArray();
        char[] needleArray = needle.toCharArray();

        int i = 0;    // 主串的初始位置
        int j = 0;    // 模式串的初始位置

        // 如果模式串为空字符串，返回0
        if (needleArray.length == 0) {
            return 0;
        }

        while (j < needleArray.length && i < haystackArray.length) {
            if (needleArray[j] == haystackArray[i]) {
                i++;
                j++;
            } else {
                i = i - j + 1;  // 一旦不匹配，i后退
                j = 0;  // j归零
            }
        }

        if (j == needleArray.length) {
            return i - j;
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String haystack = "aaaaa";
        String needle = "bba";

        String haystack1 = "miss";
        String needle1 = "miss";

        System.out.println(s.strStr(haystack, needle));
        System.out.println(s.strStr(haystack1, needle1));

    }
}
