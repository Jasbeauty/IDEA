package casting;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
//        Dog dog  = new Dog();
//        Animal animal = (Animal) dog;
//        animal.eat();
//        Mammal mam = new Dog();
//        mam.eat();
//
//        Cat cat = new Cat();
//        AnimalTrainer trainer = new AnimalTrainer();
//        trainer.teach(cat);

//        Animal anim = new Cat();
//        Cat cat = (Cat)anim;
//        SmartAnimalTrainer trainer = new SmartAnimalTrainer();
//        trainer.teach(cat);
        Set<MyObject> objects = new LinkedHashSet<MyObject>();
        objects.add(new MyObject());
        objects.add(new MyObject());
        objects.add(new MyObject());
        System.out.println(objects.size());
        while(true){
            objects.add(new MyObject());
        }
    }

//        Animal anim1 = new Cat();
//        Dog dog = (Dog) anim;

//        if (anim instanceof Cat) {
//            Cat cat = (Cat) anim;
//            cat.meow();
//        } else if (anim instanceof Dog) {
//            Dog dog = (Dog) anim;
//            dog.bark();
//        }

    // we need upcasting when we want to write general code that deals with only the supertype
    public static class AnimalTrainer {
        public void teach(Animal animal) {
            animal.move();
            animal.sleep();
        }
    }

    // Downcasting is used more frequently than upcasting. Use downcasting when we want to access specific behaviors of a subtype
    public static class SmartAnimalTrainer {
        public void teach(Animal anim) {
            // do animal-things
            anim.move();
            anim.eat();

            // if there's a dog, tell it barks
            if (anim instanceof Dog) {
                Dog dog = (Dog) anim;
                dog.bark();
            }
        }
    }
}


class MyObject {
    //设置默认数组长度为99999更快的发生OutOfMemoryError
    List<String> list = new ArrayList<>(99999);
}