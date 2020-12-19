import Decrypt.*;
import Encrypt.*;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ClientGUI extends JPanel{
   private JSplitPane splitPane;
   private JScrollPane btnPane, resultPane;
   private JPanel buttonPanel;
   private JTextField txtFirstName, txtLastName, txtCode;
   private JLabel lblFirstName, lblLastName, lblCode, lblEncryptMethod; //,lblLogMethod;
   private JComboBox cmbEncryptMethod;
   private JButton encryBtn, exitButton,decryBtn;
   private static final String ENCRYPT = "Encrypt";
   private static final String EXIT = "Exit";
   private static final String DECRYPT = "Decrypt";//&&&&
   private static final String FOLDENCRYPTION = "Folding Encrypt.Encryption";
   private static final String BIDIRSHIFTENCRYPTION = "BiShift Encrypt.Encryption";
   private static final String CAESARCYPHERENCRYPTION = "CaesarCypher Encrypt.Encryption";
   private static final Dimension minimumSize = new Dimension(400, 300);

   public static final JTextArea resultTxt = new JTextArea("Encrypted Text\n\n", 6, 20);

   public ClientGUI(){

      super(new GridLayout(1,0));
	  txtFirstName = new JTextField(12);
	  txtLastName = new JTextField(12);
	  txtCode = new JTextField(12);

	  cmbEncryptMethod = new JComboBox();
	  cmbEncryptMethod.addItem(FOLDENCRYPTION);
	  cmbEncryptMethod.addItem(BIDIRSHIFTENCRYPTION);
	  cmbEncryptMethod.addItem(CAESARCYPHERENCRYPTION);

	  lblFirstName = new JLabel("First Name:");
	  lblLastName = new JLabel("Last Name:");
	  lblCode = new JLabel("Agent Code:");
	  lblEncryptMethod = new JLabel("Encrypt.Encryption Method:");

	  //Create button objects
	  encryBtn = new JButton(ENCRYPT);
	  encryBtn.setMnemonic(KeyEvent.VK_V);
	  exitButton = new JButton(EXIT);
	  exitButton.setMnemonic(KeyEvent.VK_X);
	  decryBtn = new JButton(DECRYPT);
	  decryBtn.setMnemonic(KeyEvent.VK_V);//&&&&&


	  JPanel buttonPanel = new JPanel();

	  buttonPanel.add(encryBtn);
	  buttonPanel.add(exitButton);
	  buttonPanel.add(decryBtn);//&&&&&
	  buttonPanel.add(lblFirstName);
	  buttonPanel.add(txtFirstName);
	  buttonPanel.add(lblLastName);
	  buttonPanel.add(txtLastName);
	  buttonPanel.add(lblCode);
	  buttonPanel.add(txtCode);
	  buttonPanel.add(lblEncryptMethod);
	  buttonPanel.add(cmbEncryptMethod);

      buildUpScrollGUI();
   }

   private void buildUpScrollGUI(){
      // Setup buttonPanel, which contains all buttons and
      // will be used in btnPane below
      setUpButtonPanel();

      btnPane = new JScrollPane(buttonPanel);
      btnPane.setMinimumSize(minimumSize);

	  resultPane = new JScrollPane(resultTxt);
      splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	  splitPane.setLeftComponent(btnPane);
	  splitPane.setRightComponent(resultPane);

	  splitPane.setDividerLocation(320);
	  splitPane.setPreferredSize(new Dimension(600, 250));

	  add(splitPane);
	  setSize(new Dimension(600, 400));
      setVisible(true);
  }

  private void setUpButtonPanel(){
	 //Create buttons and add action Listener
     ButtonListener btnListener = new ButtonListener();
     encryBtn.addActionListener(btnListener);
     exitButton.addActionListener(btnListener);
     decryBtn.addActionListener(btnListener);//&&&&

     buttonPanel = new JPanel();

     GridBagLayout gridbag = new GridBagLayout();
     buttonPanel.setLayout(gridbag);
     GridBagConstraints gbc = new GridBagConstraints();

     buttonPanel.add(lblFirstName);
     buttonPanel.add(txtFirstName);
     buttonPanel.add(lblLastName);
     buttonPanel.add(txtLastName);
     buttonPanel.add(lblCode);
     buttonPanel.add(txtCode);
     buttonPanel.add(lblEncryptMethod);
     buttonPanel.add(cmbEncryptMethod);
     buttonPanel.add(encryBtn);
     buttonPanel.add(decryBtn);//&&&&&&
     buttonPanel.add(exitButton);

     gbc.insets.top = 5;
     gbc.insets.bottom = 5;
     gbc.insets.left = 5;
     gbc.insets.right = 5;

     gbc.anchor = GridBagConstraints.WEST;

     gbc.gridx = 0;
	 gbc.gridy = 0;
	 gridbag.setConstraints(lblFirstName, gbc);
	 gbc.gridx = 1;
	 gbc.gridy = 0;
	 gridbag.setConstraints(txtFirstName, gbc);
	 gbc.gridx = 0;
	 gbc.gridy = 1;
	 gridbag.setConstraints(lblLastName, gbc);
	 gbc.gridx = 1;
	 gbc.gridy = 1;
	 gridbag.setConstraints(txtLastName, gbc);
	 gbc.gridx = 0;
	 gbc.gridy = 2;
	 gridbag.setConstraints(lblCode, gbc);
	 gbc.gridx = 1;
	 gbc.gridy = 2;
	 gridbag.setConstraints(txtCode, gbc);
	 gbc.gridx = 0;
	 gbc.gridy = 3;
	 gridbag.setConstraints(lblEncryptMethod, gbc);
	 gbc.gridx = 1;
	 gbc.gridy = 3;
	 gridbag.setConstraints(cmbEncryptMethod, gbc);

	 gbc.anchor = GridBagConstraints.EAST;
     gbc.gridx = 0;
     gbc.gridy = 4;
     gridbag.setConstraints(encryBtn, gbc);
     gbc.anchor = GridBagConstraints.CENTER;
     gbc.gridx = 1;
     gbc.gridy = 4;
     gridbag.setConstraints(decryBtn, gbc);//&&&&&
     gbc.anchor = GridBagConstraints.WEST;
     gbc.gridx = 1;
     gbc.gridy = 5;
     gridbag.setConstraints(exitButton, gbc);
  }
  public String getFirstName() {
     return txtFirstName.getText();
  }
  public String getLastName() {
     return txtLastName.getText();
  }
  public String getCode() {
     return txtCode.getText();
  }
  public String getEncryptMethod() {
     return (String) cmbEncryptMethod.getSelectedItem();
  }

  /*=========================================*/
  class ButtonListener implements ActionListener
  {
    ClientGUI objClientGUI;
    Encryption info;
    Decryption infom;


    public void actionPerformed(ActionEvent e) {
	  if (e.getActionCommand().equals(EXIT)) {
           System.exit(1);
      }
      if (e.getActionCommand().equals(ENCRYPT)) {
        String firstNm = getFirstName();
		String lastNm = getLastName();
		String code = getCode();
		String encryptWay = getEncryptMethod();

        if(encryptWay.compareTo(FOLDENCRYPTION)==0)
            info = new EncryptedInfo1();
        if(encryptWay.compareTo(BIDIRSHIFTENCRYPTION)==0)
            info = new EncryptedInfo2();
        if(encryptWay.compareTo(CAESARCYPHERENCRYPTION)==0)
            info = new EncryptedInfo3();

        EncryContext con = new EncryContext(info);

        //info.log(lastNm, firstNm, code, resultTxt);

        con.doEncryption(lastNm, firstNm, code, resultTxt);

      }
      if (e.getActionCommand().equals(DECRYPT)) {//&&&&&&&
        String decryptWay = getEncryptMethod();
        if(decryptWay.compareTo(FOLDENCRYPTION)==0)
            infom = new DecryptedInfo1();
        if(decryptWay.compareTo(BIDIRSHIFTENCRYPTION)==0)
            infom = new DecryptedInfo2();
        if(decryptWay.compareTo(CAESARCYPHERENCRYPTION)==0)
            infom = new DecryptedInfo3();
        DecryContext con = new DecryContext(infom);
        con.doDecrytion(resultTxt);
      }
    }
    public ButtonListener() {
    }
    public ButtonListener(ClientGUI inObjClientGUI) {
      objClientGUI = inObjClientGUI;
    }

} // End of class ButtonHandler

  private static void createAndShowGUI(){
     JFrame.setDefaultLookAndFeelDecorated(true);
     JFrame frame = new JFrame("Bridge Pattern - Encrypt.Encryption");
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     ClientGUI newContentPane = new ClientGUI();
     newContentPane.setOpaque(true);
     frame.setContentPane(newContentPane);

     frame.pack();
     frame.setVisible(true);
  }

  static public void main(String argv[]) {
	 javax.swing.SwingUtilities.invokeLater(new Runnable() {
	    public void run() {
		   createAndShowGUI();
		}
        });
  }
}