// import jdk.internal.module.SystemModuleFinders;

/*---------------------------------------------------------*/
/* This adapter class extends InfoValidation and           */
/* implements CusInfoValidator, and so the first           */
/* 4 functionalities listed in class CusInfoValidator      */
/* are automatically inherited from class InfoValidation,  */
/* and in this addapter class, isValidSSNNum(String SSNNum)*/
/* is emplemented.                                         */
/*---------------------------------------------------------*/

class InformationAdapter extends InfoValidation implements CusInfoValidator {

	/*------------------------------------------*/
	/* The Social Security number is a 9-digit */
	/* number in the format "AAA-GG-SSSS". */
	/*------------------------------------------*/
	public boolean isValidSSNNum(String SSNNum) {
		boolean isValid = true;
		if (SSNNum.length() == 0) {
			isValid = false;
			System.out.println("Empty string ***");
		} else {
			String ns = SSNNum.trim();
			String nStr = ns.replaceAll("\\s{1,}", "");
			int len = nStr.length();

			if ((len == 11) && (nStr.charAt(3) == '-') && (nStr.charAt(6) == '-')) {
				for (int m = 0; m < len; m++) {
					if ((m != 3) && (m != 6) && (Character.isDigit(nStr.charAt(m)) == false)) {
						isValid = false;
					}
				}
			} else {
				isValid = false;
			}
		}
		return isValid;
	}

	public boolean isValidEmailAddr(String emailaddr) {
		System.out.println(emailaddr);
		if (emailaddr.length() < 5) {
			System.out.println("Empty email address***");
			return false;
		}

		String ns = emailaddr.trim();
		int len = ns.length();
		boolean dotflag = false;
		boolean atflag = false;

		for (int i = 0; i < len; i++) {
			char ch = ns.charAt(i);
			if (ch == '.') {
				dotflag = true;
				continue;
			}
			if (ch == '@') {
				atflag = true;
				continue;
			}
			if (!Character.isAlphabetic(ch) && !Character.isDigit(ch)) {
				return false;
			}
		}
		if (dotflag && atflag)
			return true;

		return false;
	}
}
