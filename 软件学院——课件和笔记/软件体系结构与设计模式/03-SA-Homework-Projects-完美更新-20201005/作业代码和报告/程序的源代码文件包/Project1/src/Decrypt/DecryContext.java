package Decrypt;

import javax.swing.*;

public class DecryContext {
    private Decryption de;
    public DecryContext(Decryption de){this.de = de;}

    public void doDecrytion(JTextArea txt){de.log(txt);}
}
