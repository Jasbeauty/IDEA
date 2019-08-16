package variable;

public class StaticTest {
    public static int x;
    public static void main(String[] args) {
//        System.out.println(x);
        System.out.println(getVal());
    }

    public static int getVal(){
        int i = 1;
        try {
            i = 4;
        }finally {
            i = i ++;
            return i;
        }
    }
}
