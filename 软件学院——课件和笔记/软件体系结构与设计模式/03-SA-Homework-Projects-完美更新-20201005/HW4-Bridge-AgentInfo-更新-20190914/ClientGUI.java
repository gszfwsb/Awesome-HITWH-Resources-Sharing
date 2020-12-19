import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ClientGUI extends JPanel{

   private JSplitPane splitPane;
   private JScrollPane btnPane, resultPane;
   private JPanel buttonPanel;

   private JTextField txtFirstName, txtLastName, txtCode;
   private JLabel lblFirstName, lblLastName, lblCode, lblEncryptMethod,lblLogMethod;
   private JComboBox cmbEncryptMethod, cmbLogMethod;
   private JButton encryBtn, exitButton;

   private static final String ENCRYPT = "Encrypt";
   private static final String EXIT = "Exit";
   private static final String FOLDENCRYPTION = "Folding Encryption";
   private static final String BIDIRSHIFTENCRYPTION = "BiShift Encryption";
   private static final String CASEARCYPHER = "Casear Cypher Encryption";
   private static final String TXTFILEWRITER = "Text File Writer";
   private static final String XMLFILEWRITER = "XML File Writer";

   private static final String DBWRITER = "Database Writer";
   public static final JTextArea resultTxt = new JTextArea("Encrypted Text\n\n", 6, 20);
   private static final Dimension minimumSize = new Dimension(400, 300);

   public ClientGUI(){

      super(new GridLayout(1,0));
	  txtFirstName = new JTextField(12);
	  txtLastName = new JTextField(12);
	  txtCode = new JTextField(12);

	  cmbEncryptMethod = new JComboBox();
	  cmbEncryptMethod.addItem(FOLDENCRYPTION);
     cmbEncryptMethod.addItem(BIDIRSHIFTENCRYPTION);
     cmbEncryptMethod.addItem(CASEARCYPHER);

	  cmbLogMethod = new JComboBox();
	  cmbLogMethod.addItem(TXTFILEWRITER);
	  cmbLogMethod.addItem(XMLFILEWRITER);
	  cmbLogMethod.addItem(DBWRITER);

	  lblFirstName = new JLabel("First Name:");
	  lblLastName = new JLabel("Last Name:");
	  lblCode = new JLabel("Agent Code:");
	  lblEncryptMethod = new JLabel("Encryption Method:");

	  lblLogMethod = new JLabel("Choose Writer:");

	  //Create button objects
	  encryBtn = new JButton(ENCRYPT);
	  encryBtn.setMnemonic(KeyEvent.VK_V);
	  exitButton = new JButton(EXIT);
	  exitButton.setMnemonic(KeyEvent.VK_X);

	  JPanel buttonPanel = new JPanel();

	  buttonPanel.add(encryBtn);
	  buttonPanel.add(exitButton);
	  buttonPanel.add(lblFirstName);
	  buttonPanel.add(txtFirstName);
	  buttonPanel.add(lblLastName);
	  buttonPanel.add(txtLastName);
	  buttonPanel.add(lblCode);
	  buttonPanel.add(txtCode);
	  buttonPanel.add(lblEncryptMethod);
	  buttonPanel.add(cmbEncryptMethod);
	  buttonPanel.add(lblLogMethod);
	  buttonPanel.add(cmbLogMethod);

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
     buttonPanel.add(lblLogMethod);
     buttonPanel.add(cmbLogMethod);
     buttonPanel.add(encryBtn);
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
	 gbc.gridx = 0;
	 gbc.gridy = 4;
	 gridbag.setConstraints(lblLogMethod, gbc);
	 gbc.gridx = 1;
	 gbc.gridy = 4;
	 gridbag.setConstraints(cmbLogMethod, gbc);

     gbc.anchor = GridBagConstraints.EAST;
     gbc.gridx = 0;
     gbc.gridy = 5;
     gridbag.setConstraints(encryBtn, gbc);
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
  public String getLogMethod() {
     return (String) cmbLogMethod.getSelectedItem();
  }

  /*=========================================*/
  class ButtonListener implements ActionListener
  {
    ClientGUI objClientGUI;
    MessageWriter writer;
    AgentInfo info;

    public void actionPerformed(ActionEvent e) {
	  if (e.getActionCommand().equals(EXIT)) {
           System.exit(1);
      }
      if (e.getActionCommand().equals(ENCRYPT)) {
        String firstNm = getFirstName();
		String lastNm = getLastName();
		String code = getCode();
		String encryptWay = getEncryptMethod();
        String logWay = getLogMethod();

        //Create a customer object
         if( logWay.compareTo(DBWRITER)==0 )
  	        writer = new DBWriter();
  	     if( logWay.compareTo(XMLFILEWRITER)==0 )
  	        writer = new XmlFileWriter();
  	     if( logWay.compareTo(TXTFILEWRITER)==0 )
            writer = new TxtFileWriter();

         if(encryptWay.compareTo(FOLDENCRYPTION)==0)
            info = new EncryptedInfo1(writer);
         if(encryptWay.compareTo(BIDIRSHIFTENCRYPTION)==0)
            info = new EncryptedInfo2(writer);
         if(encryptWay.compareTo(CASEARCYPHER)==0)
            info = new EncryptedInfo3(writer);

         info.log(lastNm, firstNm, code);
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
     JFrame frame = new JFrame("Bridge Pattern - Encryption");
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