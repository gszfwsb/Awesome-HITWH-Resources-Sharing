import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class ClientGUI extends JPanel{

   private JSplitPane splitPane;
   private JScrollPane btnPane,textPane;
   private JPanel UIPanel;
   private JTextArea dataTextArea;
   private JTextField txtCustomerName, txtAddress,
           txtZip,txtCellPhone, txtSSN, txtEmailAddr;
   private JLabel lblCustomerName, lblAddress, lblEmailAddr,
           lblZip, lblCellphone, lblSSN;
   public static final String VALIDATE = "Validate";
   public static final String EXIT = "Exit";

   public ClientGUI(){
	  super(new GridLayout(1,0));
      buildUpScrollGUI();
   }

   private void buildUpScrollGUI(){
	  setUpButtonPanel();

      dataTextArea = new JTextArea(6, 10);
	  btnPane = new JScrollPane(UIPanel);
	  textPane = new JScrollPane(dataTextArea);

  	  splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	  splitPane.setLeftComponent(btnPane);
	  splitPane.setRightComponent(textPane );

	  btnPane.setMinimumSize(new Dimension(500, 100));
	  textPane.setMinimumSize(new Dimension(500, 200));
	  splitPane.setDividerLocation(230);
	  splitPane.setPreferredSize(new Dimension(500, 400));

	  add(splitPane);
	  setSize(new Dimension(500, 400));
   }

   private void setUpButtonPanel(){
      txtCustomerName = new JTextField(20);
	  txtAddress = new JTextField(20);
	  txtZip = new JTextField(20);
	  txtCellPhone = new JTextField(20);
	  txtSSN = new JTextField(20);
	  txtEmailAddr = new JTextField(20);

	  lblCustomerName = new JLabel("Customer Name:");
	  lblAddress = new JLabel("Address:");
	  lblZip = new JLabel("Zip Code:");
	  lblCellphone = new JLabel("Cellphone Num:");
	  lblSSN = new JLabel("SSN :");
	  lblEmailAddr = new JLabel("Email Address:");

	  //Create the open button
	  JButton validateButton = new JButton(VALIDATE);
	  validateButton.setMnemonic(KeyEvent.VK_V);
	  JButton exitButton = new JButton(EXIT);
	  exitButton.setMnemonic(KeyEvent.VK_X);
	  ButtonListener objButtonHandler = new ButtonListener();

	  validateButton.addActionListener(objButtonHandler);
	  exitButton.addActionListener(objButtonHandler);

	  UIPanel = new JPanel();
	  GridBagLayout gridbag = new GridBagLayout();
	  UIPanel.setLayout(gridbag);
	  GridBagConstraints gbc = new GridBagConstraints();

	  UIPanel.add(lblCustomerName);
	  UIPanel.add(txtCustomerName);
	  UIPanel.add(lblAddress);
	  UIPanel.add(txtAddress);
	  UIPanel.add(lblZip);
	  UIPanel.add(txtZip);
	  UIPanel.add(lblCellphone);
	  UIPanel.add(txtCellPhone);
	  UIPanel.add(lblSSN);
	  UIPanel.add(txtSSN);
	  UIPanel.add(lblEmailAddr);
	  UIPanel.add(txtEmailAddr);
	  UIPanel.add(validateButton);
	  UIPanel.add(exitButton);

	  gbc.insets.top = 5;
	  gbc.insets.bottom = 5;
	  gbc.insets.left = 5;
	  gbc.insets.right = 5;
	  gbc.anchor = GridBagConstraints.WEST;

	  gbc.gridx = 0;
	  gbc.gridy = 0;
	  gridbag.setConstraints(lblCustomerName, gbc);
	  gbc.gridx = 1;
	  gbc.gridy = 0;
	  gridbag.setConstraints(txtCustomerName, gbc);
	  gbc.gridx = 0;
	  gbc.gridy = 1;
	  gridbag.setConstraints(lblAddress, gbc);
	  gbc.gridx = 1;
	  gbc.gridy = 1;
	  gridbag.setConstraints(txtAddress, gbc);
	  gbc.gridx = 0;
	  gbc.gridy = 2;
	  gridbag.setConstraints(lblZip, gbc);
	  gbc.gridx = 1;
	  gbc.gridy = 2;
	  gridbag.setConstraints(txtZip, gbc);
	  gbc.gridx = 0;
	  gbc.gridy = 3;
	  gridbag.setConstraints(lblCellphone, gbc);
	  gbc.gridx = 1;
	  gbc.gridy = 3;
	  gridbag.setConstraints(txtCellPhone, gbc);
	  gbc.gridx = 0;
	  gbc.gridy = 4;
	  gridbag.setConstraints(lblSSN, gbc);
	  gbc.gridx = 1;
	  gbc.gridy = 4;
	  gridbag.setConstraints(txtSSN, gbc);
	  gbc.gridx = 0;
	  gbc.gridy = 5;
	  gridbag.setConstraints(lblEmailAddr, gbc);
	  gbc.gridx = 1;
	  gbc.gridy = 5;
	  gridbag.setConstraints(txtEmailAddr, gbc);

	  gbc.insets.left = 2;
	  gbc.insets.right = 2;
	  gbc.insets.top = 30;

	  JPanel buttonPanel = new JPanel();
	  buttonPanel.add(validateButton);
	  buttonPanel.add(exitButton);
	  UIPanel.add(buttonPanel);
	  gbc.gridx = 1;
	  gbc.gridy = 6;
	  gridbag.setConstraints(buttonPanel, gbc);
    }

	 public String getName(){
	    return txtCustomerName.getText();
	 }
	 public String getAddress(){
		return txtAddress.getText();
	 }
	 public String getZipCode(){
		 return txtZip.getText();
	 }
	 public String getCellNum(){
		 return txtCellPhone.getText();
	 }
	 public String getSSNNum(){
		 return txtSSN.getText();
	 }

	 public String getEmailAddr(){
		 return txtEmailAddr.getText();
	 }
    class ButtonListener implements ActionListener{
       CusInfoValidator cusInfo = new InformationAdapter();
       public void actionPerformed(ActionEvent e){

          if (e.getActionCommand().equals(EXIT)){
             System.exit(1);
          }
          if (e.getActionCommand().equals(VALIDATE)){
             String name = getName();
			 String address = getAddress();
			 String zip = getZipCode();
			 String cellNum = getCellNum();
			 String ssn = getSSNNum();
			 String email = getEmailAddr();
             

             if(cusInfo.isValidName(name)==false){
			    dataTextArea.append("\nWrong format of name.");
			 }
			 else{
		        dataTextArea.append("\nCorrect format of name.");
			 }

		     if(cusInfo.isValidAddress(address)==false){
			    dataTextArea.append("\nWrong format of address.");
			 }
			 else{
			  	dataTextArea.append("\nCorrect format of address.");
			 }

		     if(cusInfo.isValidZipCode(zip)==false){
			    dataTextArea.append("\nWrong format of zip code.");
			 }
			 else{
			    dataTextArea.append("\nCorrect format of zip code.");
			 }


		     if(cusInfo.isValidCellPhoneNum(cellNum)==false){
			    dataTextArea.append("\nWrong format of cellphone number.");
			 }
			 else{
			    dataTextArea.append("\nCorrect format of cellphone number.");
			 }

		     if(cusInfo.isValidSSNNum(ssn)==false){
			    dataTextArea.append("\nWrong format of SSN.");
			 }
			 else{
			    dataTextArea.append("\nCorrect format of SSN.");
			 }

			 if(cusInfo.isValidEmailAddr(email)==false){
				 dataTextArea.append("\nWrong format of Email Address");
			 }
			 else{
				 dataTextArea.append("\nCorrect format of Email Address");
			 }
           }
        }
    } // End of class ButtonListener

    private static void createAndShowGUI() {
       JFrame.setDefaultLookAndFeelDecorated(true);
       JFrame frame = new JFrame("Adapter pattern demo");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       ClientGUI newContentPane = new ClientGUI();
       newContentPane.setOpaque(true);
       frame.setContentPane(newContentPane);
       frame.pack();
       frame.setVisible(true);
    }

    static public void main(String argv[]) {
	   javax.swing.SwingUtilities.invokeLater(new Runnable(){
          public void run(){
		     createAndShowGUI();
		  }
        });
  }
}