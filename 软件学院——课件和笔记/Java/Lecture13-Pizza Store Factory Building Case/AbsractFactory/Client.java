
public class Client {
	public static void main(String[] args) {
		AbstractFactory a = new PizzaAFactory();
		Pizza p = a.createPizza();
		
		AbstractFactory b = new PizzaBFactory();
		Pizza pp = b.createPizza();
	}
}
