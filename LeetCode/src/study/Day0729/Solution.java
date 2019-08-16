package study.Day0729;

import java.util.*;

public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null | nums2 == null) {
            return nums1 == null ? nums2 : nums1;
        } else {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int[] shortNums = nums1.length >= nums2.length ? nums2 : nums1;
            int[] longNums = nums1.length >= nums2.length ? nums1 : nums2;

            Stack<Integer> tempStack = new Stack<>();
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < shortNums.length; i++) {
                tempStack.push(shortNums[i]);
            }
            while (!tempStack.isEmpty()) {
                int param = tempStack.pop();
                circle(param, longNums, result);
            }

            // 将list转为int[]
            int[] finalResult = new int[result.size()];
            for (int i = 0; i < result.size(); i++) {
                finalResult[i] = result.get(i);
            }
            return finalResult;
        }
    }

    private List<Integer> circle(int param, int[] longNums, List<Integer> result) {
        for (int i = longNums.length - 1; i >= 0; i--) {
            if (longNums[i] == param) {
                result.add(longNums[i]);
                break;
            } else {
                continue;
            }
        }
        return result;
    }

    /**
     * 双指针法
     */
    public int[] intersect1(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        List<Integer> list = new ArrayList<>();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                list.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                i++;
            }
        }

        // list对象转数组
        int[] result = new int[list.size()];
        for (int k = 0; k < list.size(); k++) {
            result[k] = list.get(k);
        }
        return result;
    }

    /**
     * 先用Hashmap记录第一个数组中的元素【放在key】，和出现的次数【放在value】
     * 然后再遍历第二个数组
     * 如果找到对用元素&&对应HashMap中的value不为0，则添加这个元素到list中（用于之后放到数组中）
     * 同时，HashMap中的value值减 1，表示已经找到一个相同的了
     * 最后，将list中的值取出来，放到数组中返回
     */
    public int[] intersect2(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        // 先遍历第一个数组，并初始化map
        for (int i = 0; i < nums1.length; i++) {
            if (map.containsKey(nums1[i])) {
                map.put(nums1[i], map.get(nums1[i]) + 1);
            } else {
                map.put(nums1[i], 1);
            }
        }

        // 再遍历第二个数组，将map中找到的key放入list中
        LinkedList<Integer> list = new LinkedList<>();
        for (int j = 0; j < nums2.length; j++) {
            if (map.containsKey(nums2[j]) && map.get(nums2[j]) > 0) {
                list.add(nums2[j]); //添加到list中
                map.put(nums2[j], map.get(nums2[j]) - 1);
            }
        }

        //最后，将list中的值放入数组中
        int[] result = new int[list.size()];
        for (int k = 0; k < list.size(); k++) {
            result[k] = list.get(k);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{1, 1};
        int[] result = s.intersect2(nums1, nums2);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
