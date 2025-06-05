import ducks.*;
import flybehavior.*;
//import quackbehavior.*;

public class Main {
    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.display();
        mallard.performQuack();
        mallard.performFly();
        
        System.out.println("\nChanging behavior at runtime:");
        Duck model = new DecoyDuck();
        model.performFly();
        model.setFlyBehavior(new FlyWithWings());
        model.performFly();
    }
}