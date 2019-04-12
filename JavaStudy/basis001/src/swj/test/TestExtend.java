package swj.test;

public class TestExtend {
    //单例模式
    private TestExtend() {

    }

    public static TestExtend testExtend;


    public static String test() {

        String name = "111";
        return name;

    }

    public static TestExtend testExtend() {
        if (testExtend == null) {
            testExtend = new TestExtend();
        }

        return testExtend;
    }


    public void test2() {
        System.out.println("1");
    }


}
