/*==========================================================*/
/* This is the Context class in the State pattern. This  class together
/* with the state class hierarchy are so organized that the BankContext
/* contains higher business logics, and only in BankContext, the dada file
/* BankAcct.txt will be accessed.
/*==========================================================*/

import java.util.*;

public class BankContext {
  public static final double MIN_BALANCE = 2000.00;
  public static final double OVERDRAW_LIMIT = -1000.00;
  public static final double TRANS_FEE_NORMAL = 2.00;
  public static final double TRANS_FEE_OVERDRAW = 5.00;
  public static final String ERR_OVER_LIMIT = "Overdraw limit exceeded.";
  private State objState;
  private String firstNm, lastNm;
  private String acctNum;
  private double balance;
  static CustomerAcctAccess  caa = new CustomerAcctAccess();

  public BankContext(String firstNm, String lastNm, String acctNum){
	  try{
	      boolean flag = false;
	      flag = isAccount(acctNum, firstNm, lastNm); //validate account

	      if(flag == true){
	         this.firstNm = firstNm;
	         this.lastNm = lastNm;
	         this.acctNum = acctNum;
	      }
      }
      catch(Exception eee){
	     eee.printStackTrace();
      }
	  try{
	      balance = caa.getBalance(acctNum, firstNm, lastNm );
      }
      catch (Exception e){
          e.printStackTrace();
	  }
  }
  // to check if an account is valid or not
    public static boolean isAccount(String acctNum, String firstNm, String lastNm ) throws Exception{
		boolean flag = false;
		flag = caa.isAccount(acctNum, firstNm, lastNm );
		return flag;
	}

  // To create an account by writing account info to a data file
  public static void createCusNewAcct(String firstNm, String lastNm, String deposit) throws Exception{
	  caa.createNewAcct(firstNm, lastNm, deposit);
	  System.out.println("hhhhhh");
  }
  // Need to access data file
  public static String getAcctNum(String firstNm, String lastNm){
      String acctNum = null;
      acctNum = caa.getAcctNum(firstNm, lastNm);
      return acctNum;
  }

  public String getState()  {
    return objState.getState();
  }
  public void setStateObj(State objState)  {
       this.objState = objState;
  }
  // use a concrete state class to do deposit
  public void deposit(double amount){
	if( amount>0 ){
	   objState.setContext(this);
       objState.deposit(amount);
    }
    else{
	   System.out.println("Deposit amount cannot be 0 or negative");
	}
  }
  // use a concrete state class to do withdraw
  public void withdraw(double amount){
	 if( amount>0){
	    objState.setContext(this);
        objState.withdraw(amount);
     }
     else{
	 	System.out.println("Withdraw amount cannot be 0 or negative");
	 }
  }
  // Need to access data file
  public double getBalance(){
	  try{
	  	  balance = caa.getBalance(acctNum, firstNm, lastNm );
	  }
	  catch (Exception e){

	  }
	  return balance;
  }
  // Need to access data file
  public void updateBalance(double balance){
	   String strBalance = String.valueOf(balance);
       try{
			Vector newCusVector = caa.updateCusVectorData(acctNum, firstNm, lastNm, strBalance);
			caa.updateAcct(newCusVector);
		}
		catch (Exception ee){
            ee.printStackTrace();
		}
  	  this.balance = balance;
  }
  public boolean isOverDrawnLimitHit(){
	  return objState.isOverDrawnLimitReached();
  }
}
