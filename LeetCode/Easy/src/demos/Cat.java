package demos;

abstract class Animal {
    abstract void say();
}

public class Cat extends Animal {
    public Cat() {
        System.out.println("i am a cat");
    }

//    static boolean Paddy;

    public static boolean isAdmin(String userId){
        return userId.toLowerCase()=="admin";
    }
    public static void main(String[] args) {
//        Cat cat = new Cat();
//        System.out.println(Paddy);
        System.out.println(isAdmin("Admin"));
    }

    @Override
    void say() {

    }
}
