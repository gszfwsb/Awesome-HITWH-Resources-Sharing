
/*================================*/
/* This is a class to be visited  */
/*================================*/

public class DriveController implements ComputerPart{

	public static final String NAME = "DriveController";
	private final double PRICE = 20.00;
	public static final String FEATURES = "Drive Controller. Han HK8";

	public static final String KNOWLEDGE ="A divice controller is used to control and manage devices";

	public String getName(){
	   return NAME;
	}

	public double getPrice(){
        return 30.00;
    }

    public String getDescription(){
		return FEATURES;
	}

	public String getKnowledge(){
			return KNOWLEDGE;
	}

	public void accept(Visitor v){
        System.out.println("DriveController has been visited.");
        v.visitDriveController(this);
    }
}