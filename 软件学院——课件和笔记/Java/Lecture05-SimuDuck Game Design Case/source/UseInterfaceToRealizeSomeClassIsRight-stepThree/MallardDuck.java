

public class MallardDuck extends Duck implements FlyBehavior,QuackBehavior{
  public MallardDuck() {
  }
  public void display() {

    System.out.println("This is MallardDuck's display!");
  }
  public void fly()
  {
    System.out.println("MallardDuck's fly");
  }
  public void quack() {
     System.out.println("MallardDuck's quack;");
  }


}
