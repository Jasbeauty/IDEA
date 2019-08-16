package study.Day0712;

public class Solution {
    public int longestCommonSubsequence(String A, String B) {
        int[][] dp = new int[A.length() + 1][B.length() + 1];

        for (int i = 0; i < A.length(); i++) {
            for (int j = 0; j < B.length(); j++) {
                dp[i][j] = 0;
            }
        }

        for (int i = 1; i <= A.length(); i++) {
            for (int j = 1; j <= B.length(); j++) {
                if (A.toCharArray()[i - 1] == B.toCharArray()[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[A.length()][B.length()];
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String a = "ABCD";
        String b = "EDCA";
        System.out.println(s.longestCommonSubsequence(a, b));
    }
}
