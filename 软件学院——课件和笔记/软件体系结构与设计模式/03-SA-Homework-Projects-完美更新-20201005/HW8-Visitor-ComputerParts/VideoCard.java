
/*================================*/
/* This is a class to be visited  */
/*================================*/

public class VideoCard implements ComputerPart{

	public static final String NAME = "VideoCard";
    private final double PRICE = 30.00;
    public static final String FEATURES = "Video Card. TAISHAN5";

    public static final String KNOWLEDGE ="The video card is the device in a computer that outputs visual information \n"+
                                          "to the monitor. Video cards are also called video adapters or graphics cards.";


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
        System.out.println("VideoCard has been visited.");
        v.visitVideoCard (this);
    }
}