/*===============================*/
/* A concrete state in the state pattern  */
/*===============================*/

  public class OverDrawnState extends State{

    private boolean overDrawnLismitFlag = false;

	 public OverDrawnState( ){
	   state = OVERDRAWNSTATE;
    }
    public void deposit(double amount){
		if( amount>0 ){
           balance = context.getBalance()  - BankContext.TRANS_FEE_OVERDRAW;
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
		   if ((context.getBalance() - BankContext.TRANS_FEE_OVERDRAW - amount) > BankContext.OVERDRAW_LIMIT){
			  balance = context.getBalance() -BankContext.TRANS_FEE_OVERDRAW;
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
    public void sendMailToAccountHolder(){
        System.out.println ("Attention: Your Account is Overdrawn");
    }
}
