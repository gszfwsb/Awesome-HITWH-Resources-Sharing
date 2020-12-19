/*========================================*/
/* A concrete state in the state pattern  */
/*========================================*/

public class NoTransactionFeeState extends State{

   private boolean overDrawnLismitFlag = false;

   public NoTransactionFeeState(){
	  state = NOFEESTATE;
   }
   public void deposit(double amount){
	   if( amount>0 ){
          balance = context.getBalance() + amount;
          context.updateBalance(balance);
          changeState();
	   }
	   else{
	   	  System.out.println("Deposit amount cannot be 0 or negative");
	   }
   }
   public void withdraw(double amount){
	  if( amount>0 ){
		  if ((context.getBalance() - amount) > BankContext.OVERDRAW_LIMIT){
			 balance = context.getBalance()  - amount;
			 context.updateBalance(balance);
			 changeState();
		  }
		  else {
			 overDrawnLismitFlag = true;
		  }
      }
      else{
	  	  System.out.println("Withdraw amount cannot be 0 or negative");
	  }
   }
	public boolean isOverDrawnLimitReached(){
	   return overDrawnLismitFlag;
	}
}

