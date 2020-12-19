/*========================================*/
/* A concrete state in the state pattern  */
/*========================================*/

public class TransactionFeeState extends State{
    private boolean overDrawnLismitFlag = false;

	public TransactionFeeState(){
	     state = FEESTATE;
    }
    public void deposit(double amount){
		if( amount>0 ){
           balance = context.getBalance()  - BankContext.TRANS_FEE_NORMAL;
           balance = balance + amount;
           context.updateBalance(balance);
           changeState();
	     }
	     else{
		   System.out.println("Deposit amount cannot be 0 or negative");
	    }
    }
    public void withdraw(double amount){
	  if( amount>0 ){
		 if ((context.getBalance() - BankContext.TRANS_FEE_NORMAL - amount) > BankContext.OVERDRAW_LIMIT) {
			balance = context.getBalance()  - BankContext.TRANS_FEE_NORMAL;
			balance = balance - amount;
			context.updateBalance(balance);
			changeState();
		  }
		  else{
			 overDrawnLismitFlag = true;
		  }
      }
      else{
	  	  System.out.println("withdraw amount cannot be 0 or negative");
	  }

    }
    public boolean isOverDrawnLimitReached(){
   	    return overDrawnLismitFlag;
    }
}
