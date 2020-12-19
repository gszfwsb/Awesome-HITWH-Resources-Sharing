
/*================================*/
/* This is a class to be visited  */
/*================================*/

public class  Fan implements ComputerPart{

	public static final String NAME = "Fan";
    private final double PRICE = 20.00;
    public static final String FEATURES = "Fan. SDWH 665";

    public static final String KNOWLEDGE = "A fan of a computer is used to lower temperature.";



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
        System.out.println("Fan has been visited.");
        v.visitFan(this);
    }
}