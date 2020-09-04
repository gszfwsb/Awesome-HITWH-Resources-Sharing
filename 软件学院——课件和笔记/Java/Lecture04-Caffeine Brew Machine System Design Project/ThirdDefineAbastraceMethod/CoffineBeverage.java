package ThirdDefineAbastraceMethod;

public abstract class CoffineBeverage {
	public void boilWater() {
		System.out.println("Boiling water!");
	}

	public void pourInCup() {
		System.out.println("pour in cup");
	}

	public abstract void prepareRecipe();

}