

/*================================*/
/* This is a class to be visited  */
/*================================*/

public class DVDDevice implements ComputerPart{

	public static final String NAME = "DVDDevice";
	private final double PRICE = 20.00;
	public static final String FEATURES = "DVD Device. KR D380";

	public static final String KNOWLEDGE = "A type of optical disk technology similar to the CD-ROM. \n"+
	                                       "A DVD holds a minimum of 4.7GBof data, enough for a full-length movie.";

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
        System.out.println("DVDDevice has been visited.");
        v.visitDVDDevice(this);
    }
}
