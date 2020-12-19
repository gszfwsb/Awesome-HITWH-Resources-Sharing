
import java.util.ArrayList;
import java.lang.Double;

/*=========================================*/
/* This is a visitor classs in the class   */
/* diagram of the visitor design pattern   */
/*=========================================*/

public class PriceVisitor implements Visitor{
	private double total;
	public double price;
	ArrayList<Double> partsPrices;  // = new ArrayList<Double>();

	public PriceVisitor()  {
		price = 0;
		total = 0;
        partsPrices = new ArrayList<Double>();
    }

	public void visitComputerCase(ComputerCase e) {
		price = e.getPrice();
		partsPrices.add(new Double(price));
		total += price;
    }
    public void visitPowerSupply(PowerSupply e) {
		price = e.getPrice();
		partsPrices.add(new Double(price));
		total += price;
    }
    public void visitMotherboard(Motherboard e) {
		price = e.getPrice();
		partsPrices.add(new Double(price));
		total += price;
    }
    public void visitMicroprocessor(Microprocessor e) {
		price = e.getPrice();
		partsPrices.add(new Double(price));
		total += price;
    }
    public void visitMemory(Memory e){
		price = e.getPrice();
		partsPrices.add(new Double(price));
		total += price;
    }
    public void visitDriveController(DriveController e){
		price = e.getPrice();
		partsPrices.add(new Double(price));
		total += price;
    }
    public void visitHardDiskDrive(HardDiskDrive e){
		price = e.getPrice();
		partsPrices.add(new Double(price));
		total += price;
    }
     public void visitCDDrive(CDDrive  e){
		 price = e.getPrice();
		 partsPrices.add(new Double(price));
		 total += price;
    }
    public void visitDVDDevice (DVDDevice  e){
		price = e.getPrice();
		partsPrices.add(new Double(price));
		total += price;
    }
    public void visitMonitor (Monitor  e){
		price = e.getPrice();
		partsPrices.add(new Double(price));
		total += price;
    }
    public void visitKeyboard (Keyboard  e){
		price = e.getPrice();
		partsPrices.add(new Double(price));
		total += price;
    }
    public void visitMouse (Mouse  e){
		price = e.getPrice();
		partsPrices.add(new Double(price));
		total += price;
    }
    public void visitVideoCard(VideoCard e){
		price = e.getPrice();
		partsPrices.add(new Double(price));
		total += price;
    }
    public void visitFan(Fan e){
		price = e.getPrice();
		partsPrices.add(new Double(price));
		total += price;
    }

    public ArrayList<Double>  getPartsPrices() {
	   return partsPrices;
	}

    public double getPriceTotal(){
	   return total;
	}
}

