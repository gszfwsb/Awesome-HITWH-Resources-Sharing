

/*=========================================*/
/* This is the interface for the visitors  */
/* in the class diagram of the visitor     */
/* design pattern                          */
/*=========================================*/


public abstract interface Visitor{

	public abstract void visitComputerCase(ComputerCase e);
	public abstract void visitPowerSupply(PowerSupply e);
	public abstract void visitMotherboard(Motherboard e);
	public abstract void visitMicroprocessor(Microprocessor e);
	public abstract void visitMemory(Memory e);
	public abstract void visitDriveController(DriveController e);
	public abstract void visitHardDiskDrive(HardDiskDrive e);
	public abstract void visitCDDrive (CDDrive  e);
	public abstract void visitDVDDevice (DVDDevice  e);
	public abstract void visitMonitor (Monitor  e);
	public abstract void visitKeyboard (Keyboard  e);
	public abstract void visitMouse (Mouse  e);
	public abstract void visitFan (Fan  e);
	public abstract void visitVideoCard(VideoCard e);
}

