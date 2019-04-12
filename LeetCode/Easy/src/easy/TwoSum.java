package easy;

import java.util.Arrays;
import java.util.Scanner;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int[] arr = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    arr[0] = i;
                    arr[1] = j;
                    return arr;
                }
            }
        }
//        throw new IllegalArgumentException("No two sum solution");
        return arr;
    }

    public static void main(String[] args) {

       inputArr();
       inputTarget();
       TwoSum ts = new TwoSum();
       int[] arr = ts.twoSum(inputArr(),inputTarget());
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
//
//        ArrayList al = new ArrayList();
//        for (int i = 1; i < 12; i++) {
//            al.add(i);
//        }
//        al.arrs = new int[]{1, 2, 3, 4, 5};
//         System.out.println(al.contains(-1));
//         System.out.println(al.get(3));
//        al.remove(1);
//        for (int i = 0; i < al.size(); i++) {
//            System.out.println(al.get(i));
//        }
//         System.out.println(al.remove(2));
    }

    public static int[] inputArr(){
        Scanner input = new Scanner(System.in);
        System.out.println("请输入4个整数，中间以空格隔开：");
        int[] inputNum = new int[4];
        while (input.hasNextInt()){
            for (int i = 0; i < 4 ; i++) {
                inputNum[i] = input.nextInt();
//                return inputNum;
            }
            System.out.println(Arrays.toString(inputNum));
            break;
        }

        return inputNum;
    }

    public static int inputTarget(){
        Scanner input = new Scanner(System.in);
        System.out.println("请输入target值：");
        int target = 0;
        while (input.hasNextInt()){
            target = input.nextInt();
            System.out.println(target);
            break;
        }
        return target;
    }

//    数组实现ArrayList的add()、get()、remove()、contains()方法
    static class ArrayList {
        //只能用数组
//        seq表示数组下标
        private int seq = 0;

        private int[] arrs = new int[2];

        //扩容
        //Arrays.copyRange()
        public void add(int val) {
            if (seq >= arrs.length - 1) {
                //说明数组满了，需要扩容
                int[] news = new int[arrs.length * 2];
                extend(arrs, news);
            }
            arrs[seq ++] = val;
        }

        public int get(int index) {
            if(index < seq){
                return arrs[index];
            }
            return 0;
        }

        public void remove(int index) {
            int[] removed = new int[arrs.length-1];
            if (index > 0){
                System.arraycopy(arrs,0,removed,0,index);
                System.arraycopy(arrs,index+1,removed,index,arrs.length-index-1);
                seq --;
            }
//            removed = null;
            arrs = removed;
        }

        public boolean contains(int val) {
            int position = Arrays.binarySearch(arrs,val);
            return position >= 0;
        }

        /**
         * 将olds数组里面的值复制到news里面，同时重新将news结果复值到olds
         * @param olds
         * @param news
         */
        private void extend(int[] olds, int[] news) {
            System.arraycopy(olds,0,news,0,olds.length);
//            System.out.println(olds.length);
//            System.out.println(news.length);
            arrs = news;
        }

        public int size() {
            return seq;
        }
    }


}

