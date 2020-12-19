package encryptedinfo;
import messagewriter.*;
import clientGui.*;
public class EncryptedInfo3 extends AgentInfo{
    private MessageWriter writer;
    private char[] chars;
    private char[][] coupledLowerCaseChar = new char[13][2];
    private char[][] coupledUpperCaseChar = new char[13][2];

    public EncryptedInfo3(MessageWriter w) {
        writer = w;
        coupledLowerCaseChar = createCoupledCharArr("lowerCase");
        coupledUpperCaseChar = createCoupledCharArr("upperCase");
    }

    public void log(String lastNm, String firstNm, String code){
        if( isValidAgentName(lastNm) == false ||  isValidAgentName(firstNm) == false    ){
            System.out.println("Invalid agent name. Only English characters are allowed.");
            System.exit(1);
        }
        if( isValidCode(code) == false){
            System.out.println("Invalid agent code. Only English characters and numbers are allowed.");
            System.exit(1);
        }
        String fName = encryptName(firstNm);
        ClientGUI.resultTxt.append("Encrypted agent first name: "+ fName +"\n");
        String lName = encryptName(lastNm);
        ClientGUI.resultTxt.append("Encrypted agent last name: "+ lName +"\n");

        String codeStr = encryptCode(code);
        ClientGUI.resultTxt.append("Encrypted agent code: "+ codeStr +"\n\n");

        try{
            writer.logMsg(lName, fName, codeStr);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String encryptName(String inputStr){

        chars = inputStr.toCharArray();
        int length = chars.length;

        for (int i = 0; i <  length; i++) {
            char c = shiftChar(chars[i]);
            chars[i] = c;
        }
        return new String(chars);
    }

    private String encryptCode (String code) {

        char[ ] codeChars = code.toCharArray();

        if(codeChars.length != 12){
            System.out.println("Incorrect code length.");
        }
        else{
            for (int m = 0; m < codeChars.length; m++){
                if(Character.isLetter(codeChars[m]) == true){
                    char c = shiftChar(codeChars[m]);
                    codeChars[m] = c;
                }
                else if (Character.isDigit(codeChars[m]) == true){
                    char c = shiftNum(codeChars[m]);
                    codeChars[m] = c;
                }
            }
        }
        return new String(codeChars);
    }

    private char[][] createCoupledCharArr(String style){
        String lowerStr = "abcdefghijklmnopqrstuvwxyz";
        String upperStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] lowerCharArr = lowerStr.toCharArray();
        char[] upperCharArr = upperStr.toCharArray();
        char[][] coupledCharArr = new char[13][2];

        if(style.equals("lowerCase") ) {
            for(int m=0; m<13; m++){
                coupledCharArr[m][0] = lowerCharArr[2*m];
                coupledCharArr[m][1] = lowerCharArr[2*m+1];
            }
        }
        if(style.equals("upperCase") ) {
            for(int m=0; m<13; m++){
                coupledCharArr[m][0] = upperCharArr[2*m];
                coupledCharArr[m][1] = upperCharArr[2*m+1];
            }
        }
        return coupledCharArr;
    }

    private char shiftChar(char aChar){
        char inChar = aChar;
        char outChar = 'a';

        if(Character.isLowerCase(inChar)==true){
            int x = inChar + 1;
            if(x > 122){
                x = 97;
            }
            outChar = (char)x;
        }
        if(Character.isUpperCase(inChar)==true){
            int x = inChar + 1;
            if(x > 90){
                x = 65;
            }
            outChar = (char)x;
        }
        return outChar;
    }

    private char shiftNum(char intputNum){
        char shiftedNum = '0';
        int x = intputNum + 1;
        if (x > 57){
            x = 48;
        }
        shiftedNum = (char)x;
        return shiftedNum;
    }


}
