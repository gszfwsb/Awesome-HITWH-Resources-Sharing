





public class SuperFactory extends BuildingFactory {

   public House getHouse(){
      return new SupHouse();
   }

   public Condo getCondo(){
	  return new SupCondo();
   }

   public SemiDetacher getSemiDetacher(){
      return new SupSemiDe();
   }
}
