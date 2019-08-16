package variable;

public class TestA {
    private String a = "成员变量a";
    public void testA(){
//        String a = "局部变量a";
        System.out.println(a);
        System.out.println(this.a);
    }

    // 构造方法
//    public TestA() {
//        this("bb");
//        System.out.println("111");
//    }
//    // 带参数的构造方法
//    public TestA(String a) {
//        this.a = a;
//        System.out.println("222");
//    }

    public void compare(){
        String s1 = "abc";
        String s2 = "abc";
        String s3 = new String(s1);
        System.out.println(s1.equals(s2));
        System.out.println(s1==s2);
        System.out.println(s1.equals(s3));
        System.out.println(s1==s3);

        Integer a = 1271;
        int b = 1000;
        Integer c = 1271;
        int d = 1000;
        Integer e = 100;
        Integer f = 100;
        // Number objects are compared using "==", not 'equals()'
        System.out.println(c==a);
        System.out.println(b==d);
        System.out.println(e==f);
    }

    public void testString(){
        String s1 = "123";
        System.out.println(s1);
        s1 = "dde";
        System.out.println(s1);
        String s2 = new String("aaa");
        s2 = "kkk";
        System.out.println(s2);

    }

    public static void main(String[] args) {
        TestA newA = new TestA();
//        newA.a = "100";
//        System.out.println(newA);
//        newA.a = "2000";
//        System.out.println(newA);
//        newA.testA();
//        newA.compare();

        newA.testString();
    }


}

