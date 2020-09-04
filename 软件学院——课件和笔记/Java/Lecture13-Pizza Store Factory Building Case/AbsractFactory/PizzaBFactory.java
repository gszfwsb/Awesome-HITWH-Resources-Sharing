
public class PizzaBFactory extends AbstractFactory{
	public PizzaB createPizza() {
		PizzaB b = new PizzaB();
		b.bake();
		b.cut();
		b.box();
		return b;
	}
}
