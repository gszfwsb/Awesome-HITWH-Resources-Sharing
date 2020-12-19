

/*================================*/
/* This is a class to be visited  */
/*================================*/

public class CDDrive implements ComputerPart{

    public static final String NAME = "CDDrive";
	private final double PRICE = 30.00;
	public static final String FEATURES = "CD Drive. H3K";
	public static final String KNOWLEDGE = "A CD drive is used to manage disk";

	public String getName(){
		return NAME;
	}
	public double getPrice(){
        return PRICE;
    }
    public String getDescription(){
	    return FEATURES;
	}

	public String getKnowledge(){
	        return KNOWLEDGE;
	}


	public void accept(Visitor v){
        System.out.println("CDDrive has been visited.");
        v.visitCDDrive (this);
    }
}
