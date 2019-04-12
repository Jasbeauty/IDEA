package swj.test;

import swj.child.Child;
import swj.child.ChildB;
import swj.parent.Parent;

import java.nio.charset.Charset;

public class Test001 {
    public static void main(String[] args) {
        String a = "boo:and:foo";
//        System.out.println("abc"+a);
        String b = "abc".substring(2,3);
        String c = a.substring(1,2);
//        System.out.println(b);
//        System.out.println(c);
//        System.out.println(b.compareTo(c));
//
////        System.out.println(a.concat("-hello"));
//        System.out.println(a.endsWith("e"));
//        String d = "cde";
//        System.out.println(a.equals(d));
//        System.out.println(String.format("-,%s",a));
//        System.out.println(a.hashCode());
//        System.out.println(String.join("-","java","is","cool"));
//        System.out.println(a.length());
//        String[] as = a.split("o");
//        for (String str: as) {
//            System.out.println(str);
//
//        }
//        System.out.println(as.length);

        Parent parent = new Parent();

        Child child = new Child(1);

        Parent parent1 = new Child(1);
//        child.eat();

//        parent1.eat();

        Parent parent2 = new ChildB();
//        parent2.eat();

//        test3(parent2);


        String str1 = "1234";
        String str2 = String.valueOf(1);
        String str3 = "1234";
//        System.out.println(str1 == str3);

        int a1 = 128;

        int a2 = 128;

//        System.out.println(a1 == a2);
//        System.out.println(str2.hashCode());
//        System.out.println(str1.hashCode());
//        System.out.println(str3.hashCode());
        for (int i = 0; i < a.length(); i++) {
            System.out.println(a.toUpperCase().toCharArray()[i]);
        }
//        System.out.println(a.toCharArray());

        StringBuffer buffer = new StringBuffer();

        StringBuilder builder = new StringBuilder();


        int count = 0;

        Test001 test001 = new Test001();

        String s = "测试";

        String str = new String(s.getBytes(), Charset.forName("GBK"));


        String ss = new String(str.getBytes(Charset.forName("GBK")));

        System.out.println(str);
        System.out.println(ss);


    }

    static void test3(Parent parent) {
        parent.eat();
    }

    public int sum(int count) {
        return ++count;
    }

}
