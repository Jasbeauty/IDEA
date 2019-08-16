package extend;

class Base {
    public Base(String s) {
        System.out.print("B");
    }
}

public class Derived extends Base {
    public Derived(String s) {
        // super("") 必须要写，因为父类中没有无参的构造方法
        super("a");
        System.out.print("D");
    }

    public static void main(String[] args) {
        new Derived("C");
    }
}