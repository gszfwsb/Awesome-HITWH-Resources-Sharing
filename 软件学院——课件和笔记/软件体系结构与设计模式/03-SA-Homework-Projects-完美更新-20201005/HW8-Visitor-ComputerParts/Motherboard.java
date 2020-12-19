
/*================================*/
/* This is a class to be visited  */
/*================================*/

public class Motherboard implements ComputerPart{

	public static final String NAME = "Motherboard";
	private final double PRICE = 40.00;
	public static final String FEATURES = "Motherboard. Intel HB778";

	public static final String KNOWLEDGE = "The motherboard is the main component of a computer. \n"+
	                                       "It is a large rectangular board with integrated circuitry \n"+
	                                       "that connects the other parts of the computer including the \n"+
	                                       "CPU, the RAM, the disk drives (CD, DVD, hard disk, or any others) \n"+
	                                       "as well as any peripherals connected via the ports or the expansion slots.";





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

	public void accept(Visitor v) {
        System.out.println("Motherboard has been visited.");
        v.visitMotherboard(this);
    }
}

