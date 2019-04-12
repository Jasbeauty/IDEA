package easy;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {2, 7, 11, 5 };
        int target = 9;
        System.out.println((solution.twoSum(nums, target)));

    }

    /**
     * 两数相加
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        //判断特殊条件，nums==null?  nums.length == 0?
        int[] arr = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            int res = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == res) {
                    arr[0] = i;
                    arr[1] = j;
                    return arr;
                }
            }
        }
        return arr;
    }
}
