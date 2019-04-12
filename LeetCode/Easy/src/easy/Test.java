package easy;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<Integer, Integer>  map = new HashMap<>();
        map.put(2, 3);
        System.out.println(map.get(2));
        map.put(2, 4);
        System.out.println(map.get(2));
    }

    public void test() {
        System.out.println("test");
    }
}
