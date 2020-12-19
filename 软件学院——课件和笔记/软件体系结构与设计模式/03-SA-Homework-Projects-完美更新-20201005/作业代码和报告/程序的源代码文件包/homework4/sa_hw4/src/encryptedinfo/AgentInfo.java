package encryptedinfo;



public abstract class AgentInfo {

   public abstract void log(String lastNm, String firstNm, String code);

   protected static boolean isValidAgentName(String name){
       boolean flag = true;
       char[] nameChars = name.toCharArray();

       for( int m=0; m<nameChars.length; m++ ){
       if( Character.isLetter(nameChars[m]) == false )
   			 flag = false;
   	   }
          return flag;
   }

   protected static boolean isValidCode(String code){
   	   boolean flag = true;
   	   char[] codeChars = code.toCharArray();

      	   if(code.length() != 12){
   		   flag = false;
   	   }
   	   else{
   		  for( int m=0; m<codeChars.length; m++ ){
   			  if( (Character.isLetter(codeChars[m]) == false) &&
   			      (Character.isDigit(codeChars[m]) == false))
   			       flag = false;
   	      }
   	   }
   	   return flag;
   }

}