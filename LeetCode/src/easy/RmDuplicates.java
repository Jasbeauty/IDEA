package easy;

import java.util.ArrayList;
import java.util.Iterator;


public class RmDuplicates {
    public int removeDuplicates(ArrayList<Integer> nums) {

//        for (int i = 0; i < nums.size(); i++) {
//            for (int j = i+1; j < nums.size(); j++) {
//                if (nums.get(i) == nums.get(j)){
//                    nums.remove(j);
//                }
//            }
//        }





//        Iterator<Integer> integerIterator = nums.iterator();
//        while (integerIterator.hasNext()) {
//            int i = integerIterator.next();
//            int j = i + 1;
//            for (; i < nums.size(); i++) {
//                for (; j < nums.size(); j++) {
//                    if (nums.get(i) == nums.get(j)) {
//                        integerIterator.remove();
//                    }
//                }
//            }
//
//        }

        int i = 0;
        for (int j = 1; j < nums.size(); j++) {
            if (nums.get(j)!= nums.get(i)){
                i++;
                nums.set(i,nums.get(j));
            }
        }


//        return nums.size();
        return i+1;
    }

    public static void main(String[] args) {
        RmDuplicates test = new RmDuplicates();
        ArrayList<Integer> testNums = new ArrayList<>();
        testNums.add(0);
        testNums.add(0);
        testNums.add(1);
        testNums.add(1);
        testNums.add(1);
        testNums.add(2);
        testNums.add(2);
        testNums.add(3);
        testNums.add(3);
        testNums.add(4);



        System.out.println("Before: " + testNums);
        test.removeDuplicates(testNums);
        System.out.println("After: " + testNums);

    }
}
