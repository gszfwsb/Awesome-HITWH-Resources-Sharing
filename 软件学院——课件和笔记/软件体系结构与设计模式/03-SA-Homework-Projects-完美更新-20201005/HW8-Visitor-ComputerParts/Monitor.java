
/*================================*/
/* This is a class to be visited  */
/*================================*/

public class Monitor implements ComputerPart{

	public static final String NAME = "Monitor";
	private final double PRICE = 40.00;
	public static final String FEATURES = "Monitor. GZ997";

	public static final String KNOWLEDGE = "A monitor or a display is used to show what a computer is doing. ";

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
        System.out.println("Monitor has been visited.");
        v.visitMonitor(this);  // parameter "this" is harddisk
    }
}
