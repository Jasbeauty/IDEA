package swj.parent;

public interface Parents {

    default void test1() {
        System.out.println("111");
    }

    void test2();

    void test3();

}
