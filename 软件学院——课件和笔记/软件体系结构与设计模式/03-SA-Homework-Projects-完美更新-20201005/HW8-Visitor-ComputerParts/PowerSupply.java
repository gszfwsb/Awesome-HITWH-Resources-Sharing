
/*================================*/
/* This is a class to be visited  */
/*================================*/

public class PowerSupply implements ComputerPart{

	public static final String NAME = "PowerSupply";
	private final double PRICE = 20.00;
	public static final String FEATURES = "Power Supply. SHBJ6";
    public static final String KNOWLEDGE = "A power supply is a device that takes an incoming \n " +
                                           "electrical current and amplifies it to levels required by various devices.";

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
        System.out.println("PowerSupply has been visited.");
        v.visitPowerSupply(this);
    }
}
