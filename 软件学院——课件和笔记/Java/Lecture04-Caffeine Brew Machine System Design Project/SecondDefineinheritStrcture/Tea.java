package SecondDefineinheritStrcture;

public class Tea extends CoffineBeverage
{
	
		public void steepTeaBag() 
		{
			  System.out.println("steep TeaBag");
	}

	public void addLemon() 
	{
		  System.out.println("add lemon");
		}
		public void prepareRecipe(){
			  boilWater();
			  steepTeaBag();
			  pourInCup();
			  addLemon();
		}
}
