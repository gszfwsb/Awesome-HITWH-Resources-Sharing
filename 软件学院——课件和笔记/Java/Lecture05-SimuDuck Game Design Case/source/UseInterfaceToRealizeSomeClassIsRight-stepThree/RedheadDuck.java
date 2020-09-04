

public class RedheadDuck extends Duck implements FlyBehavior,QuackBehavior{
  public RedheadDuck() {
  }
  public void display() {

    System.out.println("This is RedheadDuck's display!");
  }

  /**
   * fly
   */
  public void fly() {
    System.out.println("RedheadDuck's fly();");
  }

  /**
   * quack
   */
  public void quack() {
     System.out.println("RedheadDuck's quack;");
  }

}
