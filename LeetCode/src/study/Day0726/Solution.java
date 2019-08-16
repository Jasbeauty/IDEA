package study.Day0726;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int firstIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = firstIndex; i < nums.length; i++) {
            result = cycle(firstIndex, nums, result);
            firstIndex++;
        }

        // 去重
        HashSet<List<Integer>> set = new HashSet<>(result);
        ArrayList<List<Integer>> finalResult = new ArrayList<>(set);
        return finalResult;
    }

    public List<List<Integer>> cycle(int firstIndex, int[] nums, List<List<Integer>> lists) {
        int secondIndex = firstIndex + 1;
        int sum;
        while (secondIndex < nums.length) {
            for (int i = secondIndex + 1; i < nums.length; i++) {
                sum = nums[firstIndex] + nums[secondIndex];
                if (sum + nums[i] == 0) {
                    lists.add(Arrays.asList(nums[firstIndex],nums[secondIndex],nums[i]));
                } else {
                    continue;
                }
            }
            secondIndex++;
        }
        return lists;
    }

    /**
     * 首先对数组进行排序，排序后固定一个数nums[i]，再使用左右指针指向nums[i]后面的两端，数字分别为nums[L]和nums[R]，计算三个数的和 sum，判断是否满足为0，满足则添加进结果集
     * 如果nums[i]大于0，则三数之和必然无法等于0，结束循环
     * 如果nums[i] == nums[i-1]，则说明该数字重复，会导致结果重复，所以应该跳过
     * 当sums == 0 时，nums[L] == nums[L+1]则会导致结果重复，应该跳过，L++
     * 当sum == 0 时，nums[R] == nums[R-1]则会导致结果重复，应该跳过，R--
     * 时间复杂度：O(n^2)，n为数组长度
     */
    public static List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        Arrays.sort(nums); // 排序
        int len = nums.length;
        if (nums == null || len < 3) return ans;
        for (int i = 0; i < len; i++) {
            // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            if (nums[i] > 0) {
                break;
            }
            // 去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 使用左右指针指向nums[i]后面的两端
            int L = i + 1;
            int R = len - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    // 去重
                    while (L < R && nums[L] == nums[L + 1]) {
                        L++;
                    }
                    // 去重
                    while (L < R && nums[R] == nums[R - 1]) {
                        R--;
                    }
                    L++;
                    R--;
                } else if (sum < 0) {
                    L++;
                } else if (sum > 0) {
                    R--;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{-1, 0, 1, 2, 1, -4};
        List<List<Integer>> result = s.threeSum(nums);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }
}
