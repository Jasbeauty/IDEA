package swj.child;

import swj.parent.Parent;

public class ChildB extends Parent {

    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public void eat() {
        System.out.println("childb eat");
    }
}
