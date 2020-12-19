
/*================================*/
/* This is a class to be visited  */
/*================================*/

public class Memory implements ComputerPart{

	public static final String NAME = "Memory";
	private final double PRICE = 30.00;
	public static final String FEATURES = "Memory. Intel HK55";

	public static final String KNOWLEDGE = "The main memory in a computer is called Random Access Memory. \n"+
	                                       "It is also known as RAM. This is the part of the computer \n "+
	                                       "that stores operating system software, software applications \n"+
	                                       "and other information for the central processing unit (CPU) to \n"+
	                                       "have fast and direct access when needed to perform tasks. ";

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
        System.out.println("Memory has been visited.");
        v.visitMemory(this);
    }
}