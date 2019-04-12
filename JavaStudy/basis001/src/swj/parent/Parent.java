package swj.parent;

public class Parent {

    private int num;

    public Parent() {

    }

    public Parent(int num) {
        this.num = num;
    }

    public void eat() {
        System.out.println("parent eat " + this.num);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Parent{");
        sb.append("num=").append(num);
        sb.append('}');
        return sb.toString();
    }
}
