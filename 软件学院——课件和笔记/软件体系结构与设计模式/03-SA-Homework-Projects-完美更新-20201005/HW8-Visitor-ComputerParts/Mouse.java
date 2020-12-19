
/*================================*/
/* This is a class to be visited  */
/*================================*/

public class  Mouse implements ComputerPart{

	public static final String NAME = "Mouse";
	private final double PRICE = 10.00;
	public static final String FEATURES = "Mouse. HK32";
	public static final String KNOWLEDGE = "The mouse is an effective device used by people to communicate with a computer.\n"+
	                                "For most people,the mouse is the key to operating computers and surfing Internet.";


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
        System.out.println("Mouse has been visited.");
        v.visitMouse(this);
    }
}