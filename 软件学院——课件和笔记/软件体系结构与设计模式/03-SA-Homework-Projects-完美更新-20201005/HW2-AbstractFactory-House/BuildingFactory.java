

public abstract class BuildingFactory{
  	public static final String SUPER = "Super Class";
  	public static final String MEDIUM = "Medium Class";

  	public abstract House getHouse();
	public abstract Condo getCondo();
	public abstract SemiDetacher getSemiDetacher();

  	public static BuildingFactory getFactory(String type){
  		 BuildingFactory bf = null;

  		 if (type.equals(BuildingFactory.SUPER)){
			bf = new SuperFactory();
		 }
  		 else if (type.equals(BuildingFactory.MEDIUM)){
			bf = new MediumFactory();
		 }
         return bf;
    }
}
