public class Mocha extends CondimentDecorator {
	public Mocha(Beverage beverage) {
		this.setBeverage(beverage);
	}

	public String getDescription() {
		return this.getBeverage().getDescription() + ", Mocha";
	}

	public double cost() {
		return 0.20 + this.getBeverage().cost();
	}
}
