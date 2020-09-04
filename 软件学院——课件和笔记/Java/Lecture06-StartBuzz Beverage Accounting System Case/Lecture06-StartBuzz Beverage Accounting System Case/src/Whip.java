public class Whip extends CondimentDecorator {

	public Whip(Beverage beverage) {
		this.setBeverage(beverage);
	}

	public String getDescription() {
		return this.getBeverage().getDescription() + ", Whip";
	}

	public double cost() {
		return 0.40 + this.getBeverage().cost();
	}
}
