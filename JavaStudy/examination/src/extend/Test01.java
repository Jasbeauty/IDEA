package extend;

public class Test01 {
    public static void main(String[] args) {
        int i=126;
        Integer j = 126;
        Integer k = 126;
        System.out.println(i==j);
        System.out.println(j.equals(i));
        System.out.println(k == j);

        Test01 test01 = new Test01();
        test01.setVar(1, 2, 3);
    }

//    public void setVar(int a, int b, float c) {}

    public int setVar(int a, int b, float c) {return 1;}



}
