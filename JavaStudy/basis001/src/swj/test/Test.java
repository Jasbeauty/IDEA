package swj.test;

public interface Test {
    void test1();

    void test2();

    void test3();

    void test4();

    void test5();

    void test6();

    void test7();

    void test8();

    default void test9() {
        System.out.println("10");
    }

}
