
/*================================*/
/* This is a class to be visited  */
/*================================*/

public class  Keyboard implements ComputerPart{

	public static final String NAME = "Keyboard";
	private final double PRICE = 30.00;
	public static final String FEATURES = "Keyboard. WH33";

	public static final String KNOWLEDGE = "The keyboard is an effective device used by people to communicate with a computer.\n"+
	                                       "For most people,the keyboard is the key to operating computers and surfing Internet.";

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
        System.out.println("Keyboard has been visited.");
        v.visitKeyboard(this);
    }
}
