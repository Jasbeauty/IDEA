package swj.child;

import swj.parent.Parent;

public class Child extends Parent {
    public Child() {
        super();
    }

    public Child(int num) {
//        super();
        super(num);
    }

    public Child(String str) {
//        super();
        super(1);
    }

//    @Override
//    public void eat() {
//        System.out.println("child eat");
//    }

}
