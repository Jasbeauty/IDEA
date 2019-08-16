package study.Day0816;

import study.BackTrackingMethod.ListSort;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> aIntegers = new ArrayList<>();

        solve(aIntegers, 0, nums, list);
        return list;
    }

    private static void solve(List<Integer> aIntegers, int n, int[] nums, List<List<Integer>> list) {
        if (n == nums.length) {
            list.add(new ArrayList<>(aIntegers));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (aIntegers.contains(nums[i])) {
                continue;
            }
            aIntegers.add(nums[i]);
            solve(aIntegers, n + 1, nums, list);
            aIntegers.remove(aIntegers.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution s  = new Solution();
        int[] nums = new int[]{1, 1, 2};
        List<List<Integer>> result = s.permuteUnique(nums);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }
}
