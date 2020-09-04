package StepSeven;

public class MallardDuck extends Duck {
	
	
	public MallardDuck() {
		setFlyBehavior(new FlyWithWings());
		setQuackBehavior(new Quack());			
	}
	
	public void display() {
		System.out.println("I'm a real Mallard duck");
	}
}
