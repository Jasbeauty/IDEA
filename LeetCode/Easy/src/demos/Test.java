package demos;

public class Test {

    public static void main(String[] args) {

        System.out.println(Son.val);
//        Son[] son = new Son[10];


    }
}

class GrandParent {
    static {
        System.out.println("i am grand parent");
    }
//    public static int val = 100;

}


class Parent extends GrandParent {
    static {
        System.out.println("i am parent");
    }

//    public static int val = 100;
}


class Son extends Parent {
    static {
        System.out.println("i am son");
    }
    public static final   int val = 100;

}