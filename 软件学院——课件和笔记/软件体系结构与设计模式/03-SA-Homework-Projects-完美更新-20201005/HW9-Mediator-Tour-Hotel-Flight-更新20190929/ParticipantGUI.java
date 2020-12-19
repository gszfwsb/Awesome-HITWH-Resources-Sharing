
public interface ParticipantGUI{

   public abstract void addCustomer(String cus);
   public abstract void addPossibleCustomer(String c);
   public abstract void displayInfo(String txt);
   public abstract void writeCusToXmlFile(String[] cusInfo) throws Exception;
   public abstract void writeCandidateCusToXmlFile(String[] cusInfo ) throws Exception;
}