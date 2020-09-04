

public class MiniDuckSimulator {
  public MiniDuckSimulator() {
  }
  public static void main(String[] args)
  {
    MallardDuck mallardDuck = new MallardDuck();
    RedheadDuck redheadDuck = new RedheadDuck();
    RubberDuck rubberDuck = new RubberDuck();
    DecoyDuck decoyDuck = new DecoyDuck();

    mallardDuck.fly();
    mallardDuck.quack();

    redheadDuck.fly();
    redheadDuck.quack();

    System.out.println("RubberDuck has not implements the flyBehavior so it has only quack() method");
    rubberDuck.quack();


    System.out.println("DecoyDuck has not implements the flyBehavior and QuackBehavior");
    decoyDuck.display();



  }

}
