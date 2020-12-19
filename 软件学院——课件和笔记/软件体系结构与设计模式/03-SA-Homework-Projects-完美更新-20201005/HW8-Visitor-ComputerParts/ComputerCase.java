
/*================================*/
/* This is a class to be visited  */
/*================================*/

public class ComputerCase implements ComputerPart{

	public static final String NAME = "ComputerCase";
	private final double PRICE = 30.00;
    public static final String FEATURES = "Computer Case. Green Hoke C668";

    public static final String KNOWLEDGE = "A commputer case of a computer is a hardware device \n"+
	                                       "that's used to hold all computer parts.";

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
        System.out.println("ComputerCase has been visited.");
        v.visitComputerCase(this);
    }
}
