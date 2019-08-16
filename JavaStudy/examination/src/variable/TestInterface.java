package variable;

import java.util.HashMap;
import java.util.Map;

public interface TestInterface {

    default void test(){
        System.out.println("11");
    }


    void test2();

}
