
/*================================*/
/* This is a class to be visited  */
/*================================*/

public class Microprocessor implements ComputerPart{

	public static final String NAME = "Microprocessor";
	private final double PRICE = 80.00;
	public static final String FEATURES = "Microprocessor. Intel BJ786";

	public static final String KNOWLEDGE = "The central processing unit (CPU) performs most of the \n"+
	                                       "calculations which enable a computer to function, and \n"+
	                                       "is sometimes referred to as the brain of the computer.";



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
        System.out.println("Microprocessor has been visited.");
        v.visitMicroprocessor(this);
    }
}
