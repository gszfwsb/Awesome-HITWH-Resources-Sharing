package FiveAddTempleMethod;

public abstract class CoffineBeverage {
	public void boilWater() {
		System.out.println("Boiling water!");
	}

	public void pourInCup() {
		System.out.println("pour in cup");
	}
	
	public void prepareRecipe() {
		boilWater();
		brew();
		pourInCup();
		addCondiments();
	}
	public abstract void brew();
	public abstract void addCondiments();
	


}