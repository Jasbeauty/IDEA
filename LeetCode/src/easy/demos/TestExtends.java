package easy.demos;

public class TestExtends {
    public static void main(String[] args) {
        SonClass s = new SonClass(66);
    }
}

class FooClass {
    public FooClass() {
        System.out.println(100);
    }

    public FooClass(int count) {
        System.out.println(count);
    }
}

class SonClass extends FooClass {
    public SonClass() {
    }

    public SonClass(int c) {
        super(2);             // 显式调用父类构造函数后,系统就不在默认调用无参构造函数了
        System.out.println(1234);   // 在执行下面这句时，系统会优先调用父类的无参构造函数 super();
    }

}
