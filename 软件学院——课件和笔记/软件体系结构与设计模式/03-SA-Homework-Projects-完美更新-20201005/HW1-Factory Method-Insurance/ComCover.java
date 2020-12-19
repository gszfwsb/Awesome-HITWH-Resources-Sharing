


public class ComCover implements AutoInsurance{
   private String description;

   public String getInfo(){
	description = "Comprehensive Coverage: \n\nPays for damage to or loss of your "+
	              "car in the event of fire, theft or "+
	              "vandalism. Again, your lender may "+
	              "require this coverage if your car is financed.";
	return description;
   }
}