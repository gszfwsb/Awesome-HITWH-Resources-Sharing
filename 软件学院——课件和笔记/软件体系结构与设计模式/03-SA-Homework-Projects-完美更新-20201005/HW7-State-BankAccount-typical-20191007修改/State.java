 /*=============================================================*/
 /* This is the State class in the State pattern. This class has 3 subclasses:
 /* NoTransactionFeeState,TransactionFeeState and OverDrawnState.
 /*=============================================================*/

 public abstract class State {
    private String acctNumber;
    protected double balance =0;
    protected String state;
    protected static final String NOFEESTATE="NoTransFeeState";
    protected static final String FEESTATE="TransFeeState";
    protected static final String OVERDRAWNSTATE="OverDrawnState";
	protected static final String ERRORSTATE="ErrorState";
	protected static final String TAXSTATE="TaxState";

    protected State stateObj;
    protected BankContext context;

    protected void changeState(){
		balance = context.getBalance();
	   if(balance<0 && balance >= BankContext.OVERDRAW_LIMIT)
	      state = OVERDRAWNSTATE;
	   else if (balance >= BankContext.MIN_BALANCE)
	      state = NOFEESTATE;
	   else if (balance >= 0 && balance < BankContext.MIN_BALANCE)
	      state = FEESTATE;
	   else
	      state = ERRORSTATE;
	   passStateObjToContext();
	}
	public void passStateObjToContext(){
	   if(state.equals(OVERDRAWNSTATE)  )
	      stateObj = new OverDrawnState();
	   else if (state.equals(NOFEESTATE) )
	      stateObj = new  NoTransactionFeeState();
	   else if (state.equals(FEESTATE))
	      stateObj = new  TransactionFeeState();
	   context.setStateObj(stateObj);
	}
	public String getAcctNum(){
	   return acctNumber;
	}
	public String getState(){
	   return state;
	}
	public void setContext(BankContext context){
	    this.context = context;
    }

    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);
    public abstract boolean isOverDrawnLimitReached();
}
