package study.Day0711;

public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length >= 2) {
            String result = "";
            char[] longArr;
            char[] shortArr;

            // 选出前两个字符串较短的那个，记为shortArr，用于之后的循环比较
            if (strs[0].length() > strs[1].length()) {
                longArr = strs[0].toCharArray();
                shortArr = strs[1].toCharArray();
            } else {
                shortArr = strs[0].toCharArray();
                longArr = strs[1].toCharArray();
            }
            // 找出第一、二个字符串的最长公共前缀，记为firstRecord
            String firstRecord = circle(longArr, shortArr);
            if (strs.length > 2) {
                for (int i = 2; i < strs.length; i++) {
                    // 将上面的firstRecord值和后面一位的字符串长度进行比较，选出较短的那个，用于之后的循环判断
                    if (strs[i].length() == firstRecord.length()) {
                        shortArr = strs[i].toCharArray();
                        longArr = firstRecord.toCharArray();
                    } else {
                        shortArr = strs[i].length() < firstRecord.length() ? strs[i].toCharArray() : firstRecord.toCharArray();
                        longArr = strs[i].length() > firstRecord.length() ? strs[i].toCharArray() : firstRecord.toCharArray();
                    }
                    result = circle(longArr, shortArr);
                }
            } else {
                return firstRecord;
            }
            return result;
        } else if (strs.length == 0) {
            return "";
        } else {
            return strs[0];
        }
    }

    private String circle(char[] longArr, char[] shortArr) {
        StringBuilder sb = new StringBuilder();

        // 先获得前两个字符串的最长公共前缀，记为record
        for (int j = 0; j < shortArr.length; j++) {
            char temp = shortArr[j];
            // 如果第一个字符就不匹配，就直接跳出循环
            if (temp != longArr[j]) {
                break;
            } else {
                sb.append(shortArr[j]);
            }
        }

        return sb.toString();
    }

    public String longestCommonPrefix1(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                System.out.println(strs[i].indexOf(prefix));
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }

    public String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0)
            return "";
        String ans = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            for (; j < ans.length() && j < strs[i].length(); j++) {
                if (ans.charAt(j) != strs[i].charAt(j))
                    break;
            }
            ans = ans.substring(0, j);
            if (ans.equals(""))
                return ans;
        }
        return ans;
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        String[] test = new String[]{"flower", "flow", "flight", "ss"};
        String[] test1 = new String[]{"c", "c"};

        System.out.println(s.longestCommonPrefix1(test));
        System.out.println(s.longestCommonPrefix1(test1));
    }
}
