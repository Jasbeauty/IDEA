package study.Day0811;

public class NumArray {
    int[] getNums;

    public NumArray(int[] nums) {
        getNums = nums;
    }

    // 通过将下标为 i 的数值更新为 val
    public void update(int i, int val) {
        getNums[i] = val;
    }


    // 数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点
    public int sumRange(int i, int j) {
        int sum = 0;
        for (int k = i; k <= j; k++) {
            sum += getNums[k];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5};
        NumArray obj = new NumArray(nums);
        System.out.println(obj.sumRange(0, 2));
        obj.update(1, 2);
        System.out.println(obj.sumRange(0, 2));
    }
}
