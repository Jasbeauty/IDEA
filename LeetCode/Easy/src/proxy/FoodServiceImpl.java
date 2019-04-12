package proxy;

public class FoodServiceImpl implements FoodService {
    public Food makeChicken(){
        Food f = new Chicken();
        f.setChicken("1kg");
        f.setSpicy("1g");
        f.setSalt("3g");
        return f;
    }
}
