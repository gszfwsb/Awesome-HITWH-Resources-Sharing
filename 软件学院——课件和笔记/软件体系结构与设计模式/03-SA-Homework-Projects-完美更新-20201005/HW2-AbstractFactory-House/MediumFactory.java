



public class MediumFactory extends BuildingFactory {

   public House getHouse(){
      return new MedHouse();
   }

   public Condo getCondo(){
	  return new MedCondo();
   }

   public SemiDetacher getSemiDetacher(){
      return new MedSemiDe();
   }

}
