package easy;

public class Quicksort {
    public static void main(String[] args) {
        int[] test = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1};
        Quicksort qs = new Quicksort();
        qs.sort(test, 0, test.length - 1);
        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + " ");
        }
    }

    // low是自定义的第一个位置，high是自定义的最后一个位置
    public void sort(int[] nums, int low, int high) {
        // 左指针（数组中第一个位置）
        int start = low;
        // 右指针（数组中最后一个位置）
        int end = high;
        // 基准值（默认选第一个元素）
        int sign = nums[start];

        // 直到两个指针相遇的时候，结束循环，对初步排序后基准值前后的数进行递归
        while (start < end) {
            // 从最后往前遍历
            // 只要值大于等于基准值，就继续往前挪一位
            while (nums[end] >= sign && start < end) {
                end--;
            }
            // 当找到比基准值小的值，就交换两个位置上的值
            if (nums[end] < sign) {
                int temp = nums[end];
                nums[end] = nums[start];
                nums[start] = temp;
            }

            // 从开头往后遍历
            // 只要值小于等于基准值，就继续往后挪一位
            while (nums[start] <= sign && start < end) {
                start++;
            }
            // 当找到比基准值大的值，就交换两个位置上的值
            if (nums[start] > sign) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
            }
        }

        // 对基准值前面的数进行递归
        if (start > low) {
            sort(nums, low, start - 1);
        }
        // 对基准值后面的数进行递归
        if (high > end) {
            sort(nums, end + 1, high);
        }
    }
}
