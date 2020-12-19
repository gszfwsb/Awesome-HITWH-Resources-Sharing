
import java.util.Vector;

/*=========================================*/
/* This is a visitor classs in the class   */
/* diagram of the visitor design pattern   */
/*=========================================*/

public class KnowledgeVisitor implements Visitor {

	private String partInfo;
	private String allOders=" ";

	public void visitComputerCase(ComputerCase e) {
		partInfo = e.getKnowledge();
		allOders = allOders + "\n " + partInfo;
	}
	public void visitPowerSupply(PowerSupply e) {
		partInfo = e.getKnowledge();
		allOders = allOders + "\n " + partInfo;
	}
	public void visitMotherboard(Motherboard e) {
		partInfo = e.getKnowledge();
		allOders = allOders + "\n " + partInfo;
	}
	public void visitMicroprocessor(Microprocessor e) {
		partInfo = e.getKnowledge();
		allOders = allOders + "\n " + partInfo;
	}
	public void visitMemory(Memory e){
		partInfo = e.getKnowledge();
		allOders = allOders + "\n " + partInfo;
	}
	public void visitDriveController(DriveController e){
		partInfo = e.getKnowledge();
		allOders = allOders + "\n " + partInfo;
	}
	public void visitHardDiskDrive(HardDiskDrive e){
		partInfo = e.getKnowledge();
		allOders = allOders + "\n " + partInfo;
	}
	 public void visitCDDrive(CDDrive  e){
		partInfo = e.getKnowledge();
		allOders = allOders + "\n " + partInfo;
	}
	public void visitDVDDevice (DVDDevice  e){
		partInfo = e.getKnowledge();
		allOders = allOders + "\n " + partInfo;
	}
	public void visitMonitor (Monitor  e){
		partInfo = e.getKnowledge();
		allOders = allOders + "\n " + partInfo;
	}
	public void visitKeyboard (Keyboard  e){
		partInfo = e.getKnowledge();
		allOders = allOders + "\n " + partInfo;
	}
	public void visitMouse (Mouse  e){
		partInfo = e.getKnowledge();
		allOders = allOders + "\n " + partInfo;
	}
	public void visitVideoCard(VideoCard e){
		partInfo = e.getKnowledge();
		allOders = allOders + "\n " + partInfo;
	}
	public void visitFan(Fan e){
		partInfo = e.getKnowledge();
		allOders = allOders + "\n " + partInfo;
	}

	public String getPartDescription(String partName) {
	   return partInfo;
	}

	public String printAllOrderInfo(){
	   return allOders;
	}
}

