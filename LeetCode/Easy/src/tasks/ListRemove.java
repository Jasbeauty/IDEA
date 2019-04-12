package tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListRemove {

    public static void main(String[] args) {

//    int[] a = new int[]{1,2,3,3,4,5};
        List<Integer> a = new ArrayList<>(Arrays.asList(1, 2, 3, 3, 4, 5));

//        List<Integer> b = new ArrayList<>(a);



        for(Integer s : a){
            if (s==3){
                a.remove(s);
                break;
            }
        }

        System.out.println(a.size());
        System.out.println(a);

//        for (int i = 0; i < a.size(); i++) {
//            if (a.get(i)==3){
//                a.remove(i);
//                i--;
//            }
//        }
//
//        System.out.println(a.size());
//        System.out.println(a);
//
//
//        Iterator<Integer> integerIterator = a.iterator();
//        while (integerIterator.hasNext()) {
//            int val = integerIterator.next();
//            if (val == 3) {
//                integerIterator.remove();
//            }
//        }
//        System.out.println(a.size());
//        System.out.println(a);
        //深拷贝，浅拷贝

    }


}
