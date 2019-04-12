package swj.test;

import java.util.Arrays;

public class Hello {
    public static void main(String[] args) {
        System.out.println();
        Hello hello = new Hello();
        hello.test1(1);

        int[] values = new int[]{1, 3, 5, 7, 9, 2, 4, 6, 8};
        Arrays.sort(values);


        int []keys = new int[1];
        int ks[] = new int[10];

        int[][] ts = new int[1][];
        int[][] bs = new int[2][1];
//        int[][] ss = new int[][1];

        hello.test1("1");

        String t = "123456";
        System.out.println(t.startsWith("4", 3));

        char t1 ='a';
        int t3 = t1 + 1;
        System.out.println(t3);
        String t2 = "dsdsdd";
        System.out.println(t1);
        System.out.println(t2);

        String name = TestExtend.test();

        TestExtend testExtend = TestExtend.testExtend();

//        TestExtend testExtend1 = new TestExtend();
        testExtend.testExtend();

        Hello hello1 = new Hello();
        hello1.test6();
    }


    void test1(int num) {

    }

    int test1(Hello num) {
        return 1;
    }

    void test1(String ...str) {

    }

    Integer test2() {
        return Integer.valueOf("1");
    }

    int test3() {
        return 1;
    }

    void test4() {
        int a = 1;
        int b = 1;

        Integer c = new Integer(1);
        Integer d = new Integer(1);

        int m = c;

        String ss = "111";
        String ss1 = "111";
        String s = new String("111");
        String str = new String("233");


    }

    public static void test6() {
        System.out.println("1");
    }

}
