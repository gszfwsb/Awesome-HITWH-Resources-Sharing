package ForthUnifiedMethodName;

public class Tea extends CoffineBeverage {

	public void brew() {
		System.out.println("steep TeaBag");
	}

	public void addCondiments() {
		System.out.println("add lemon");
	}

	public void prepareRecipe() {
		boilWater();
		brew();
		pourInCup();
		addCondiments();
	}

}
