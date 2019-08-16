package study.Day0709;

public class Solution {
    public String multiply(String num1, String num2) {
        // 特殊情况
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        // 一般两个数相乘，其结果的位数也不会大于两个数的位数之和
        int[] result = new int[num1.length() + num2.length()];

        int temp;
        for (int i = num2.length() - 1; i >= 0; i--) {
            for (int j = num1.length() - 1; j >= 0; j--) {
                // 字符转换为数字（'0'的ASCII码为48）
                temp = (num1.charAt(j) - '0') * (num2.charAt(i) - '0') + result[i + j + 1];
                // 处理进位
                result[i + j] += temp / 10;     // 进位的值
                result[i + j + 1] = temp % 10;      // 当前位的值
            }
        }

        StringBuilder sb = new StringBuilder();
        int index = 0;

        // 把高位的0去掉
        while (index < result.length && result[index] == 0) {
            index++;
        }

        // 将数组转换为字符串
        for (int i = index; i < result.length; i++) {
            sb.append(result[i]);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String num1 = "12";
        String num2 = "45";
        System.out.println(s.multiply(num1, num2));
    }
}
