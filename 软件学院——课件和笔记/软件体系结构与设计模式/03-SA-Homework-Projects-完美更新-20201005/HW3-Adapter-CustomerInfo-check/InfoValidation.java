
class InfoValidation {

   /*----------------------------------------------------------------------------------*/
   /* The name should be at least one character long           */
   /* and digital numbers should not appear in the names  */
   /*----------------------------------------------------------------------------------*/
   public boolean isValidName(String name){
	   boolean isValid=true;
	   if(name.length() == 0){
	           isValid=false;
	   }
	   else{
			   String ns = name.trim();
			   String nStr = ns.replaceAll("\\b\\s{1,}\\b", "");
			   int len = nStr.length();

			   if(len != 0 ){
				  for(int m=0; m<len; m++){
					if(Character.isLetter(nStr.charAt(m))==false)
						isValid=false;
				  }
			   }
			   else{
				  isValid=false;
			   }
		 }
		 return isValid;
   }
   /*-----------------------------------------------------------------------------*/
   /* The address should be at least 10 character long   */
   /*-----------------------------------------------------------------------------*/
   public boolean isValidAddress(String address){
       boolean isValid = true;
       if(address.length() == 0){
		     isValid = false;
		     System.out.println("Empty string 2***");
	   }
	   else{
			   char[] ca = address.trim().toCharArray();
			   int aLen = ca.length;

			   if ( aLen < 10 ){
				    isValid = false;
			   }
	    }
	    return  isValid;
   }
   /*----------------------------------------------------------------------------------*/
   /* The zip code should contain exactly 9 digital integer   */
   /* numbers. Only digital numbers are allowed in the zip  */
   /* code. Spaces are allowed in the zip code                        */
   /*----------------------------------------------------------------------------------*/
   public boolean isValidZipCode(String zipCode){

       boolean isValid=true;
	   if(zipCode.length() == 0){
		        isValid = false;
		        System.out.println("Empty string 3***");
	   }
	   else{
			   String ns = zipCode.trim();
			   String nStr = ns.replaceAll("\\b\\s{1,}\\b", "");
			   int len = nStr.length();

			  if (len == 9){
				 for(int n=0; n<len; n++){
					if(Character.isDigit(nStr.charAt(n))==false){
					   isValid = false;
					}
				 }
			  }
			  else{
				  System.out.println("Length is not 9");
				  isValid = false;
			   }
       }
       return  isValid;
   }

   /*-------------------------------------------------------------------------------------------*/
   /* The cellPhone number should contain exactly 11 digital       */
   /* integer numbers. Only digital numbers are allowed in            */
   /* the zip code.Spaces are allowed in the zip code                      */
   /*-------------------------------------------------------------------------------------------*/
   public boolean isValidCellPhoneNum(String phoneNum){
       boolean isValid=true;

       if(phoneNum.length() == 0){
		      isValid = false;
		       System.out.println("Empty string 4***");
	   }
	   else{
			   String ns = phoneNum.trim();
			   String nStr = ns.replaceAll("\\b\\s{1,}\\b", "");
			   int len = nStr.length();

			  if (len == 11 ){
					 for(int n=0; n<len; n++){
						if(Character.isDigit(nStr.charAt(n))==false){
							isValid = false;
						}
					 }
			  }
			  else{
				  System.out.println("Length is not 11");
				  isValid = false;
			  }
		   }
		return isValid;
     }
}// end of class
