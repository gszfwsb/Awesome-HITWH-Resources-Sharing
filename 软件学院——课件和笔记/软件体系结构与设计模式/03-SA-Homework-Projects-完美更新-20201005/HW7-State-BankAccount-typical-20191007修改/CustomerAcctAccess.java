/*------------------------------------------------*/
/* This class includes methods for accessing data file
/* BankAcct.txt
/*------------------------------------------------*/

import java.util.*;

public class CustomerAcctAccess{
  private String fileName = "BankAcct.txt";

  public void createNewAcct(String firstNm, String lastNm, String deposit) throws Exception{
	  //generate a new account number
	  int top = 100000000;
	  Random rand = new Random();
      int acctNum = rand.nextInt(top);
      String acctStrNum = String.valueOf(acctNum);

      FileUtil fu = new FileUtil(fileName);
      String dataLine = acctStrNum + " " + firstNm + " " + lastNm + " " + deposit;
      fu.appendToFile(dataLine,true);
  }
  public String getAcctNum(String firstNm, String lastNm) {
	  String acctNum = null;
	  Vector allCusData = getAllCusData();
	  for (int i = 0; i < allCusData.size(); i++) {
	  	   String aCusData = (String)allCusData.get(i);
	  	   String[] aCusArr = aCusData.split(" ");

	  	   if( (aCusArr[1]).equals(firstNm) == true && (aCusArr[2]).equals(lastNm) == true  ){
	  			acctNum  =  aCusArr[0];
	  	   }
	  }
      return acctNum;
  }

    // To check if <acctNum, firstNm, lastNm> represents an account
    public boolean isAccount(String acctNum, String firstNm, String lastNm ) throws Exception{
  	   boolean flag = false;
  	   Vector allCusData = getAllCusData();
  	   for (int i = 0; i < allCusData.size(); i++) {
  	       String aCusData = (String)allCusData.get(i);
  	       String[] aCusArr = aCusData.split(" ");

  	       if( (aCusArr[0]).equals(acctNum) == true &&
  	           (aCusArr[1]).equals(firstNm) == true &&
  	           (aCusArr[2]).equals(lastNm) == true ){
  			   flag = true;
  	       }
	   }
       return flag;
  }

  // Update account by writing back all customers' info from
  // a Vector, which holds all info with new balance
  public void updateAcct(Vector cusDataVector) throws Exception{
       FileUtil futil = new FileUtil(fileName);
       futil.writeVectorToFile(cusDataVector);
  }

  // Read a line from the txt data file
  public String readALineFromAccount(String acctNum, String lastNm, String firstNm) throws Exception{
       FileUtil futil = new FileUtil(fileName);
       String aLine = futil.readFromFile();
       return aLine;
  }
  // update balance of a vector by the given <acctNum, firstNm, lastNm> info
  public Vector updateCusVectorData(String acctNum, String firstNm, String lastNm, String newBalance ) throws Exception{
  	   double balance = 0;
  	   Vector allCusData = getAllCusData();
  	   for (int i = 0; i < allCusData.size(); i++) {
  	       String aCusData = (String)allCusData.get(i);
  	       String[] aCusArr = aCusData.split(" ");

  	       if( (aCusArr[0]).equals(acctNum) == true && (aCusArr[1]).equals(firstNm) == true && (aCusArr[2]).equals(lastNm) == true ){

			   allCusData.setElementAt((aCusArr[0]) + " " + (aCusArr[1]) + " "  +(aCusArr[2]) + " " + newBalance,i);
  		   }
  	   }
       return allCusData;
  }
  // get balance of a <acctNum, firstNm, lastNm>
  public double getBalance(String acctNum, String firstNm, String lastNm ) throws Exception{
	   double balance = 0;
	   Vector allCusData = getAllCusData();
	   for (int i = 0; i < allCusData.size(); i++) {
	       String aCusData = (String)allCusData.get(i);
	       String[] aCusArr = aCusData.split(" ");

	       if( (aCusArr[0]).equals(acctNum) == true && (aCusArr[1]).equals(firstNm) == true && (aCusArr[2]).equals(lastNm) == true ){
			   balance =  Double.parseDouble(aCusArr[3]);
		   }
	   }
       return balance;
  }

  public Vector getAllCusData(){
	  FileUtil futil = new FileUtil(fileName);
	  Vector cusData = futil.readFile();
	  return cusData;
  }
  public void printAllCusData(){
	  Vector allCusData = getAllCusData();
	  for (int i = 0; i < allCusData.size(); i++) {
      	System.out.println(allCusData.get(i));
	  }
  }
}
