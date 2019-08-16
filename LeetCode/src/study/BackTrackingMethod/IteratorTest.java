package study.BackTrackingMethod;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorTest {
    public static void main(String[] args) {
        List<String> aa = new ArrayList<>();
        aa.add("f1");
        aa.add("f2");
        aa.add("f3");

        Iterator<String> it = aa.iterator();
        while (it.hasNext()) {
            String str = it.next();

            if ("f3".equals(str)) {
                it.remove();
            }
        }

        for (String temp :
                aa) {
            System.out.println(temp);
        }
    }
}
