
/*----------------------------------------------------*/
/* This interface is implemented by class Information */
/* Adapter, which inherits class InfoValidation.      */
/*----------------------------------------------------*/

public interface CusInfoValidator{
	public abstract boolean isValidName(String name);
	public abstract boolean isValidAddress(String address);
	public abstract boolean isValidZipCode(String zipCode);
	public abstract boolean isValidCellPhoneNum(String phoneNum);
	public abstract boolean isValidSSNNum(String SSNNum);
	public abstract boolean isValidEmailAddr(String emailaddr);
}

