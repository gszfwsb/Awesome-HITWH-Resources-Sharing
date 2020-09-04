

public class MiniDuckSimulator {
  public MiniDuckSimulator() {
  }
  public static void main(String[] args)
  {
    MallardDuck mallardDuck = new MallardDuck();
    RedheadDuck readheadDuck = new RedheadDuck();
    RubberDuck rubberDuck = new RubberDuck();

    // print information of MallardDuck
    System.out.println("---------------------------------");
    System.out.println("Step One Information:");
    System.out.println("The following are MallardDuck's behavior:   ");
    mallardDuck.swim();
    mallardDuck.quack();
    mallardDuck.display();
    System.out.println("Step Two Information");
    mallardDuck.fly();
    System.out.println("---------------------------------");
    System.out.println("");
    System.out.println("");
    //print information of RedheadDuck
    System.out.println("---------------------------------");
    System.out.println("Step One Information:");
    System.out.println("The following are RedheadDuck's behavior:");
    readheadDuck.swim();
    readheadDuck.quack();
    readheadDuck.display();
    System.out.println("Step Two Information");
    readheadDuck.fly();
    System.out.println("---------------------------------");

    System.out.println("Step Two Information:");
    System.out.println("The following are RubberDuck's behavior:");
    rubberDuck.display();
    System.out.println("Rubber Duck can not fly, But if we use rubberDuck.fly(), the following will happen:");
    rubberDuck.fly();



  }

}
