package SecondDefineinheritStrcture;

public class Coffee extends CoffineBeverage
{
  
   public void brewCoffeeGrinds()
   {
   	   System.out.println("brew coffee grinds");
   }
  
   public void addSugarAndMilk()
   {
   	   System.out.println("add sugar and milk");
   }
   public void prepareRecipe()
   {
   	   boilWater();
   	   brewCoffeeGrinds();
   	   pourInCup();
   	   addSugarAndMilk();
   }
}