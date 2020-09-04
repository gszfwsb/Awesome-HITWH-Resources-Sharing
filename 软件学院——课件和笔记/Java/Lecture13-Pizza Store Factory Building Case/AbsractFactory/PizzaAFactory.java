
public class PizzaAFactory extends AbstractFactory{
	public PizzaA createPizza() {
		PizzaA a = new PizzaA();
		a.bake();
		a.cut();
		a.box();
		return a;
	}
}
