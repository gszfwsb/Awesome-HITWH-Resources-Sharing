public abstract class CondimentDecorator extends Beverage {
	private Beverage beverage;

	public Beverage getBeverage() {
		return beverage;
	}

	public void setBeverage(Beverage beverage) {
		this.beverage = beverage;
	}
}
