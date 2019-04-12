package swj.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class TestImpl implements Test {
    @Override
    public void test1() {

    }

    @Override
    public void test2() {

    }

    @Override
    public void test3() {
        System.out.println("11");
        test9();

    }

    @Override
    public void test4() {

    }

    @Override
    public void test5() {
        Test t = new TestImpl();
    }

    @Override
    public void test6() {

    }

    @Override
    public void test7() {

    }

    @Override
    public void test8() {
        try {
            InputStream stream = new FileInputStream(".");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
