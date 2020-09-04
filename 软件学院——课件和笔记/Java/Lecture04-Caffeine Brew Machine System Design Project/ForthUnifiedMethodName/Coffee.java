package ForthUnifiedMethodName;

public class Coffee extends CoffineBeverage {

	public void brew() {
		System.out.println("brew coffee grinds");
	}

	public void addCondiments() {
		System.out.println("add sugar and milk");
	}

	public void prepareRecipe() {
		boilWater();
		brew();
		pourInCup();
		addCondiments();
	}

}