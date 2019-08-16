package study.BackTrackingMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定数组[1,2,3]，输出所有的排列组合
 */
public class ListSort {
    public List<List<Integer>> permute(int[] nums) {
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
        ListSort listSort = new ListSort();
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> result = listSort.permute(nums);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }
}
