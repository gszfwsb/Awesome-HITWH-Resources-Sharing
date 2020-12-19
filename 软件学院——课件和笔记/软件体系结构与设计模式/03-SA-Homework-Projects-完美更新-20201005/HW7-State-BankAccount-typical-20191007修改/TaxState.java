public class TaxState extends State{
    private boolean taxFlag = false;
    public TaxState(){
        state = TAXSTATE;
    }

    public void deposit(double amount) {
        if (amount<0) {
            System.out.println("Deposit amount cannot be 0 or negative");
            return;
        }
        if (amount>0){
            if (amount>100000){
                balance = context.getBalance();
                balance = balance+amount*(1-0.05);
                context.updateBalance(balance);
                taxFlag = true;
            }
            else taxFlag=false;
        }
        else{
            System.out.println("withdraw amount cannot be 0 or negative");
        }

    }

    public void withdraw(double amount) {
        if (amount<0) {
            System.out.println("Deposit amount cannot be 0 or negative");
            return;
        }
        if (amount>0){
            if (amount>100000){
                if ((context.getBalance()-amount)>BankContext.OVERDRAW_LIMIT){
                    balance = context.getBalance();
                    balance = balance-amount;
                    context.updateBalance(balance);
                    changeState();
                }
            }
        }
        else{
            System.out.println("withdraw amount cannot be 0 or negative");
        }

    }

    public boolean isOverDrawnLimitReached() {
        return taxFlag;
    }
}
