


public class HardDiskDrive implements ComputerPart{

	public static final String NAME = "HardDiskDrive";
	private final double PRICE = 20.00;
	public static final String FEATURES = "Hard DiskDrive. TAISHAN380";

	public static final String KNOWLEDGE = "A hard disk drive (HDD) is a hardware device that's used \n"+
	                                       "to store information like software and files.";

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
        System.out.println("HardDiskDrive has been visited.");
        v.visitHardDiskDrive(this);
    }
}

