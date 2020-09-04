

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class MiniDuckSimulator {
  public static void main(String[] args)
  {
    //MallardDuck mallardDuck = new MallardDuck();
    //RubberDuck rubberDuck = new RubberDuck();
    Duck mallardDuck = new MallardDuck();
    Duck rubberDuck = new RubberDuck();
    // print information of MallardDuck
    System.out.println("---------------------------------");
    System.out.println("The following are MallardDuck's behavior");
    mallardDuck.swim();
    mallardDuck.quack();
    mallardDuck.display();

    //print information of RubberDuck
    System.out.println("---------------------------------");
    System.out.println("The following are RubberDuck's behavior");
    rubberDuck.swim();
    rubberDuck.quack();
    rubberDuck.display();
    new RedHeadDuck().display();
  }
}
