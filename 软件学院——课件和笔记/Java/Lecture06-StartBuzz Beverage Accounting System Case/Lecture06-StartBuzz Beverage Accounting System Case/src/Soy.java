
public class Soy extends CondimentDecorator {

	public Soy(Beverage beverage) {
		this.setBeverage(beverage); 
	}
	public String getDescription() {
		return this.getBeverage().getDescription() + ", Soy";
	}
	public double cost() {
		return 0.30 + this.getBeverage().cost();
	}
}
