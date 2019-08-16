package casting;

public abstract class Animal implements Mammal{

    public void eat() {
        System.out.println("eating...");
    }

    public void move() {
        System.out.println("moving...");
    }

    public void sleep() {
        System.out.println("sleeping...");
    }
}
