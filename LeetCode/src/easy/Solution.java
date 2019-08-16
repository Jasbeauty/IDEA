package easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums1 = new int[]{100, 1, 11, 1, 120, 111, 123, 1, -1, -100};


        int[] result = s.nextGreaterElement2(nums1);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }


//        int[] result = s.nextGreaterElements(nums1);
//        for (int i = 0; i < result.length; i++) {
//            System.out.println(result[i]);
//        }

//        int[] result1 = s.nextGreaterElement1(nums1);
//        for (int i = 0; i < result1.length; i++) {
//            System.out.println(result1[i]);
//        }


//        int[] result = s.nextGreaterElement(nums1);
//        for (int i = 0; i < result.length; i++) {
//            System.out.println(result[i]);
//        }
    }

    public int[] nextGreaterElement1(int[] nums) {
        int[] nums2 = addLengthArray(nums);

        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums[i] == nums2[j]) {
                    System.out.println("j: " + j);
                    for (int k = j; k < nums2.length; k++) {
                        if (k == nums2.length - 1) {
                            result[i] = -1;
                            break;
                        } else if (nums2[k + 1] > nums[i]) {
                            result[i] = nums2[k + 1];
                            break;
                        }
                    }
                }
            }
        }

        return result;
    }


    public int[] nextGreaterElement2(int[] nums) {
        int[] nums2 = addLengthArray(nums);

        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> hasMap = new HashMap<>();

        int[] result = new int[nums.length];

        // 先遍历大数组nums2，首先将第一个元素入栈；当遍历完nums2后，得到nums2中元素所对应的下一个更大元素的hashMap
        for (int i = 0; i < nums2.length; i++) {
            // 继续遍历
            while (!stack.isEmpty() && stack.peek() < nums2[i]) {
                // 当当前元素大于栈顶元素时，栈顶元素出栈，此时应将该出栈的元素（key：当前元素）与当前元素（value：当前元素所对应的下一个更大元素）形成key-value键值对，存入HashMap中
                hasMap.put(stack.pop(), nums2[i]);
            }
            // 当当前元素小于栈顶元素时，继续将它入栈；
            if (i < nums.length) {
                stack.push(nums2[i]);
            }

        }

        // 遍历nums1的元素（作为key）在hashMap中去查找下一个更大元素（作为value），当找不到时则为默认值-1
        for (int i = 0; i < nums.length; i++) {
            result[i] = hasMap.getOrDefault(nums[i], -1);
        }

        return result;
    }

    // 数组扩容
    public static int[] addLengthArray(int[] nums) {
        int[] nums2 = new int[nums.length * 2];
        System.arraycopy(nums, 0, nums2, 0, nums.length);   // 先变成{5,4,3,2,1,0,0,0,0,0}

        // 将原数组的值，按顺序复制到后面扩容出来的位置上
        for (int j = nums2.length - 1; j > nums.length - 1; j--) {
            nums2[j] = nums2[j - nums.length];
        }
        return nums2;   // 最后变成{5,4,3,2,1,5,4,3,2,1}
    }

    public int[] nextGreaterElement(int[] nums) {

        //stack中的值表示：暂时还未找到比nums[]大的数
        //a是把nums[]复制扩容一份后的数组，更便于循环比较
        int[] stack = new int[nums.length * 2];
        int[] a = new int[nums.length * 2];

        // res[]每位初始化的是原数组长度的 -1
        int[] res = new int[nums.length];
        // Arrays.fill(): assigns the specified data type value to each element of the specified range of the specified array
        Arrays.fill(res, -1);

        for (int i = 0; i < nums.length; i++) {
            a[i] = a[i + nums.length] = nums[i];
        }

        int top = 0, j = 0;
        while (j < a.length) {
            if (top == 0) {
                stack[top++] = j;
            } else {
                while (top > 0 && a[j] > a[stack[top - 1]]) {
                    if (stack[top - 1] >= nums.length) {
                        res[stack[top - 1] - nums.length] = a[j];
                    } else {
                        res[stack[top - 1]] = a[j];
                    }
                    top--;
                }
                stack[top++] = j;
            }
            j++;
        }
        return res;
    }

    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        // 先定义一个默认全是 -1 的原数组长度的数组resArr
        int[] resArr = new int[n];
        Arrays.fill(resArr, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 2 * n; i++) {
            int curNum = nums[i % n];
            while (!stack.isEmpty() && nums[stack.peek()] < curNum) {
                resArr[stack.pop()] = curNum;
            }
            if (i < n) {
                stack.push(i);
            }
        }
        return resArr;
    }

}