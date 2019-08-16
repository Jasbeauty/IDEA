package easy.demos;

public class ClassOuter {
    private int noStaticInt = 1;
    private static int STATIC_INT = 2;

    public void fun() {
        System.out.println("外部类方法");
    }

    public class InnerClass {
        // static int num = 1;  // 此时编辑器会报错 非静态内部类则不能有静态成员
        public void fun(){
            // 非静态内部类的非静态成员可以访问外部类的非静态变量
            System.out.println(STATIC_INT);
            System.out.println(noStaticInt);
        }
    }

    public static class StaticInnerClass {
        static int NUM = 1;     // 静态内部类可以有静态成员
        public void fun(){
            System.out.println(STATIC_INT);
            // System.out.println(noStaticInt);     // 此时编辑器会报不可访问外部类的非静态变量错
        }
    }
}