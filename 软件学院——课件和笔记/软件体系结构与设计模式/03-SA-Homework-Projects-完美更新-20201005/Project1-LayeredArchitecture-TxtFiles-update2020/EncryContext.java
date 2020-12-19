
import javax.swing.*;

public class EncryContext{

    private Encryption en;

	public EncryContext(Encryption en){
		this.en = en;
	}

	public void doEncryption(String lastNm, String firstNm, String code, JTextArea txt){
		en.log(lastNm, firstNm, code, txt);
	}
}