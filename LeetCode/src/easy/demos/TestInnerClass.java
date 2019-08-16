package easy.demos;



public class TestInnerClass {
    public static void main(String[] args) {
        // 非静态内部类 创建方式 1
        ClassOuter.InnerClass innerClass = new ClassOuter().new InnerClass();
        // 非静态内部类 创建方式 2
        ClassOuter outer = new ClassOuter();
        ClassOuter.InnerClass inner = outer.new InnerClass();
        // 静态内部类的创建方式
        ClassOuter.StaticInnerClass staticInnerClass = new ClassOuter.StaticInnerClass();
    }
}
