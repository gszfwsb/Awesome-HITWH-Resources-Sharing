import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JScrollPane;

/*=================================================*/
/* This is a GUI for testing the state pattern.    */
/*=================================================*/
public class ClientGUI extends JPanel{
   private JSplitPane splitPane;
   private JScrollPane textPane;
   private JTextArea transactTxtArea;
   private JPanel btnPanel;
   private JComboBox cmbTransType;
   private JTextField txtAmount;
   private JTextField txtAcctNum;
   private JTextField txtName;
   private JLabel lblTransType;
   private JLabel lblTransAmount;
   private JLabel lblAcctNum;
   private JLabel lblName;
   public static final String DEPOSIT = "Deposit";
   public static final String WITHDRAW = "Withdraw";
   public static final String SUBMIT = "Submit";
   public static final String EXIT = "Exit";
   public static final String OPEN = "Open Account";

   private BankContext accContext;
   State initState = new TransactionFeeState();

   public ClientGUI(){
	  super(new GridLayout(1,0));
      buildUpScrollGUI();
   }
   private void buildUpScrollGUI(){
	  setUpButtonPanel();
      transactTxtArea = new JTextArea(8, 2);
	  textPane = new JScrollPane(transactTxtArea);
	  textPane.setMinimumSize(new Dimension(100, 100));
	  splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, btnPanel, textPane);
	  splitPane.setDividerLocation(220);
	  add(splitPane);
	  setSize(new Dimension(700, 800));
   }
   private void setUpButtonPanel(){
      cmbTransType = new JComboBox();
      cmbTransType.addItem(DEPOSIT);
      cmbTransType.addItem(WITHDRAW);

      txtName = new JTextField(13);
      txtAmount = new JTextField(13);
      txtAcctNum = new JTextField(13);

      lblName = new JLabel("Name: ");
      lblAcctNum = new JLabel("Accout number:");
      lblTransType = new JLabel("Transaction Type:");
      lblTransAmount = new JLabel("Transaction Amount:");

      JButton srchButton = new JButton(SUBMIT);
      srchButton.setMnemonic(KeyEvent.VK_S);
      JButton exitButton = new JButton(EXIT);
      exitButton.setMnemonic(KeyEvent.VK_X);
      JButton openButton = new JButton(OPEN);
      exitButton.setMnemonic(KeyEvent.VK_X);

      ButtonListener listener = new ButtonListener();
      srchButton.addActionListener(listener);
      exitButton.addActionListener(listener);
      openButton.addActionListener(listener);

      btnPanel = new JPanel();
      GridBagLayout gridbag = new GridBagLayout();
      btnPanel.setLayout(gridbag);
      GridBagConstraints gbc = new GridBagConstraints();

      btnPanel.add(lblName);
      btnPanel.add(txtName);
      btnPanel.add(lblAcctNum);
      btnPanel.add(txtAcctNum);
      btnPanel.add(lblTransType);
      btnPanel.add(cmbTransType);
      btnPanel.add(lblTransAmount);
      btnPanel.add(txtAmount);
      btnPanel.add(srchButton);
      btnPanel.add(exitButton);
      btnPanel.add(openButton);

      gbc.insets.top = 5;
      gbc.insets.bottom = 5;
      gbc.insets.left = 5;
      gbc.insets.right = 5;

      gbc.anchor = GridBagConstraints.EAST;
      gbc.gridx = 0;
      gbc.gridy = 0;
      gridbag.setConstraints(lblName, gbc);
      gbc.anchor = GridBagConstraints.WEST;
      gbc.gridx = 1;
      gbc.gridy = 0;
      gridbag.setConstraints(txtName, gbc);
      gbc.anchor = GridBagConstraints.EAST;
	  gbc.gridx = 0;
	  gbc.gridy = 1;
	  gridbag.setConstraints(lblAcctNum, gbc);
	  gbc.anchor = GridBagConstraints.WEST;
	  gbc.gridx = 1;
	  gbc.gridy = 1;
      gridbag.setConstraints(txtAcctNum, gbc);
      gbc.anchor = GridBagConstraints.EAST;
      gbc.gridx = 0;
      gbc.gridy = 2;
      gridbag.setConstraints(lblTransType, gbc);
      gbc.anchor = GridBagConstraints.WEST;
      gbc.gridx = 1;
      gbc.gridy = 2;
      gridbag.setConstraints(cmbTransType, gbc);
      gbc.gridx = 0;
	  gbc.gridy = 3;
	  gridbag.setConstraints(lblTransAmount, gbc);
	  gbc.anchor = GridBagConstraints.WEST;
	  gbc.gridx = 1;
	  gbc.gridy = 3;
      gridbag.setConstraints(txtAmount, gbc);
      gbc.insets.left = 2;
      gbc.insets.right = 2;
      gbc.insets.top = 25;
      gbc.anchor = GridBagConstraints.EAST;
      gbc.gridx = 0;
      gbc.gridy = 6;
      gridbag.setConstraints(srchButton, gbc);
      gbc.anchor = GridBagConstraints.WEST;
      gbc.gridx = 1;
      gbc.gridy = 6;
      gridbag.setConstraints(exitButton, gbc);
      gbc.anchor = GridBagConstraints.WEST;
	  gbc.gridx = 3;
	  gbc.gridy = 6;
      gridbag.setConstraints(openButton, gbc);
   }

  public String getName(){
      String nm = txtName.getText();
      String name = nm.trim();
      return name;
  }
  public String getAcctNum(){
      String aNum = txtAcctNum.getText();
      String accNum = aNum.trim();
      return accNum;
  }
  public String getTransactionType(){
     return (String) cmbTransType.getSelectedItem();
  }
  public String getTransactionAmount(){
     String amt = txtAmount.getText();
     String a = amt.trim();
     return a;
  }
  public BankContext getAccount(){
     return accContext;
  }
  public void setResult(String transactiontxt){
     transactTxtArea.append(transactiontxt);
  }

  class ButtonListener implements ActionListener {
     public void actionPerformed(ActionEvent e){
        String transactiontxt = null;
        boolean result = false;
        if (e.getActionCommand().equals(EXIT)){
           System.exit(1);
        }
        if (e.getActionCommand().equals(OPEN)){
			String name = getName();
			String amount = getTransactionAmount();
			String[] nameArr = name.split(" ");
            try{
			    BankContext.createCusNewAcct(nameArr[0], nameArr[1], amount);
		    }
		    catch(Exception e3){
				e3.printStackTrace();
			}
			String acctNum = BankContext.getAcctNum(nameArr[0], nameArr[1]);
			setResult("姓名： " + name + "\n请记住你的新开账号号码： " + acctNum);
			setResult("\nYour balance is ： " + amount);
		}
        if (e.getActionCommand().equals(SUBMIT)){
		   boolean flag = false;
		   String name = getName();
		   String acctNum = getAcctNum();
    	   String type = getTransactionType();
    	   String amount =  getTransactionAmount();
    	   String[] nameArr = name.split(" ");

           if((nameArr[0]).length() > 0 && (nameArr[1]).length() > 0 && acctNum.length() > 0 ){
			   //boolean flag = false;
			   try{
			       flag = BankContext.isAccount(acctNum, nameArr[0], nameArr[1]);

			       if(flag == true){
				       accContext = new BankContext(nameArr[0], nameArr[1],acctNum);
	                   initState.setContext(accContext);
	                   initState.changeState();
	                   initState.passStateObjToContext();
				   }
				   else{
					   setResult("输入姓名、或者账号有错误\n");
					   //System.exit(0);
				   }
		       }
		       catch(Exception exx){
                   exx.printStackTrace();
			   }
		   }
		   else{
			   setResult("姓名、账号不能为空\n");
		   }

           if(flag == true) {
			   if (type.equals(DEPOSIT)){
				  if(accContext != null)
					  accContext.deposit( new Double(amount).doubleValue());
			   }
			   if (type.equals(WITHDRAW)){
				  if(accContext != null)
					  accContext.withdraw( new Double(amount).doubleValue());
			   }
			   if(accContext != null){
				   transactiontxt =  "\nTransaction Successful: " +
									 "\nCustomer name:  " + name +
									 "\nAccount number: " + acctNum +
									 "\nNew Balance = " + accContext.getBalance() +
									 "\nNew State = "+accContext.getState() + "\n";
			   }
			   if((accContext != null) && accContext.isOverDrawnLimitHit()==true)
				   transactiontxt = BankContext.ERR_OVER_LIMIT;
			   setResult(transactiontxt);
		   }

         }
      }
  }
  private static void createAndShowGUI(){
     JFrame.setDefaultLookAndFeelDecorated(true);
     JFrame frame = new JFrame("State Pattern- Bank Account");
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
      }  });
  }
}

