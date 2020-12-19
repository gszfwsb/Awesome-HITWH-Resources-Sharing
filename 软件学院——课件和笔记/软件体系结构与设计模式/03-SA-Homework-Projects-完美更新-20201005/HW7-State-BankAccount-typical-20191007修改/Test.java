
import java.util.*;

public class Test{

    public static void main(String[] v){
	    CustomerAcctAccess c = new CustomerAcctAccess();
	    c.printAllCusData();
	    try{
     	    double b = c.getBalance("00000003", "Linda", "White");
     	    System.out.println("Balance =" + b );
        }
        catch (Exception e){

		}
	    //System.out.println("Balance =" + b );
	    try{

			//Vector newCusVector = c.updateCusVectorData("00000003", "Tom", "White", "9000000" );
			//FileUtil fu = new FileUtil("BankAcct.txt");

			//fu.setupFile();
			//c.updateAcct(newCusVector);

            //fu.vectorToFile(newCusVector);
            //fu.closeFile();

            //FileUtil fu = new FileUtil();
            //fu.vectorToFile(newCusVector, "BankAcct.txt");

            //for (int i = 0; i < newCusVector.size(); i++) {
  	        //     System.out.println((String)newCusVector.get(i));
		    //}
		    CustomerAcctAccess caa = new CustomerAcctAccess();
		    caa.createNewAcct("Lilly", "Clinton");


		}
		catch (Exception ee){

		}
    }
}