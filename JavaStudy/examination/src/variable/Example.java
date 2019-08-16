package variable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Example {
    String str = new String("good");
    transient String str1 = "bad";

    public static void main(String args[]) {
        Example ex = new Example();
        ex.change(ex.str);
        ex.change(ex.str1);
        System.out.println(ex.str);
        System.out.println(ex.str1);
//
        String s1 = "123";
//        System.out.println("s1 = " + s1);
//        s1 = "dde";
//        System.out.println("s1 = " + s1);
//        String s2 = new String("aaa");
//        System.out.println("s2 = " + s2);
//        s2 = "kkk";
//        System.out.println("s2 = " + s2);
//
//        ex.change(s1);
//        System.out.println(s1);
//        String[] arrs = new String[] {"2", "1"};
//        ex.change1(arrs);
//        System.out.println(arrs[0]);
//
//        int num = 3;
//        ex.change2(num);
//        System.out.println(num);
//
//        List<String> list = new ArrayList<>();
//        ex.change3(list);
//        System.out.println(list.size());
//
//        System.out.println('a' - 1);

        ArrayList<String> list1 = new ArrayList<>(Arrays.asList(new String[] {"1", "2"}));
        System.out.println(list1.size());

    }

    public void change(String str) {
        str1 = "bad test";
        str = "good test";

    }

    private void change1(String[] arrs) {
        arrs = new String[] {"1", "2"};
//        arrs[0] = "1";
    }

    private int change2(int num) {
        num = 2;
        return num;
    }

    private void change3(List<String> list) {
        list.add("1");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Example example = (Example) o;
        return Objects.equals(str, example.str) &&
                Objects.equals(str1, example.str1);
    }

    @Override
    public int hashCode() {

        return Objects.hash(str, str1);
    }
}