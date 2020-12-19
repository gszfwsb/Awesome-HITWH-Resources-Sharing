import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class PipeFilterTestGui extends JPanel{

   private JScrollPane btnPane;
   private JSplitPane splitPane;
   private JSplitPane rightSplitPane;
   private JTextArea originalTxt;
   private JTextArea resultTxt;
   private JScrollPane originPane;
   private JScrollPane resultPane;
   private JLabel filelbl;
   private JPanel buttonPanel;
   private JComboBox cmbFileList;
   static final String PROCESS = "Process";
   static final String EXIT = "Exit";
   static final String ORIGINALFILES = "OriginalFiles/";
   static final Dimension minimumSize = new Dimension(230, 200);

   public PipeFilterTestGui(){
      super(new GridLayout(1,0));
	  resultTxt = new JTextArea("Encrypted File Text\n\n", 6, 20);
	  originalTxt = new JTextArea("Original File Text: \n\n", 6, 20);
      buildUpScrollGUI();
   }

   private void buildUpScrollGUI(){
      // Setup buttonPanel, which contains all buttons and
      // will be used in btnPane below
      setUpButtonPanel();

      btnPane = new JScrollPane(buttonPanel);
      btnPane.setMinimumSize(minimumSize);

	  resultPane = new JScrollPane(resultTxt);
      originPane = new JScrollPane(originalTxt);

	  rightSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	  rightSplitPane.setLeftComponent(originPane);
	  rightSplitPane.setRightComponent(resultPane);
	  rightSplitPane.setDividerLocation(190);

	  splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	  splitPane.setLeftComponent(btnPane);
	  splitPane.setRightComponent(rightSplitPane);

	  splitPane.setDividerLocation(220);
	  splitPane.setPreferredSize(new Dimension(600, 250));

	  add(splitPane);
	  setSize(new Dimension(600, 400));
      setVisible(true);
  }

  private void setUpButtonPanel(){

	 cmbFileList = new JComboBox();
	 String[] cl = extractFileList();
     setupFileList(cmbFileList,cl);

     filelbl = new JLabel("Files:");

     //Create buttons
     JButton srchButton = new JButton(PROCESS);
     srchButton.setMnemonic(KeyEvent.VK_S);
     JButton exitButton = new JButton(EXIT);
     exitButton.setMnemonic(KeyEvent.VK_X);

     ButtonListener btnListener = new ButtonListener();

     // add action Listener
     srchButton.addActionListener(btnListener);
     exitButton.addActionListener(btnListener);

     buttonPanel = new JPanel();

     GridBagLayout gridbag = new GridBagLayout();
     buttonPanel.setLayout(gridbag);
     GridBagConstraints gbc = new GridBagConstraints();

     buttonPanel.add(filelbl);
     buttonPanel.add(cmbFileList);
     buttonPanel.add(srchButton);
     buttonPanel.add(exitButton);

     gbc.insets.top = 5;
     gbc.insets.bottom = 5;
     gbc.insets.left = 5;
     gbc.insets.right = 5;

     gbc.anchor = GridBagConstraints.EAST;

     gbc.gridx = 0;
     gbc.gridy = 0;
     gridbag.setConstraints(filelbl, gbc);
     gbc.anchor = GridBagConstraints.WEST;
     gbc.gridx = 1;
     gbc.gridy = 0;
     gridbag.setConstraints(cmbFileList, gbc);
     gbc.anchor = GridBagConstraints.EAST;
     gbc.gridx = 0;
     gbc.gridy = 1;
     gbc.insets.left = 2;
     gbc.insets.right = 2;
     gbc.insets.top = 25;
     gbc.anchor = GridBagConstraints.EAST;
     gbc.gridx = 0;
     gbc.gridy = 3;
     gridbag.setConstraints(srchButton, gbc);
     gbc.anchor = GridBagConstraints.WEST;
     gbc.gridx = 1;
     gbc.gridy = 3;
     gridbag.setConstraints(exitButton, gbc);
  }

  /*=========================================*/
  /* Get a selected file name from User GUI  */
  /*=========================================*/
  public String getSelectedFile() {
     return (String) cmbFileList.getSelectedItem();
  }
  /*================================================*/
  /* Extract file names from a diectory on your     */
  /* computer                                       */
  /*================================================*/
  public String[] extractFileList(){
     File f = new File(ORIGINALFILES);
     String [] fileNames = f.list();
     return fileNames;
  }
  /*==============================================*/
  /* Add car list to combox cmbFileList. Both     */
  /* objects cmbFileList and files are passed     */
  /* in from parameters.                          */
  /*==============================================*/
  public void setupFileList(JComboBox cmbFileList,String[] files){
     for(int k=0; k<files.length; k++){
        cmbFileList.addItem(files[k]);
     }
  }

  class ButtonListener implements ActionListener{
     String carPrice;

     public void actionPerformed(ActionEvent e){
        String searchResult = null;

        if (e.getActionCommand().equals(EXIT)){
           System.exit(1);
        }
        if (e.getActionCommand().equals(PROCESS)){
           String selectedFile = getSelectedFile();
          try{
			 originalTxt.append("File Name: " + selectedFile+"\n\n");
		   	 resultTxt.append("From file: " + selectedFile + "\n");
		   	 AssemblyOfPipeLines apline = new AssemblyOfPipeLines();
		   	 apline.assembly(selectedFile, originalTxt, resultTxt);
		   }
		   catch (IOException ex){  }
        }
     }
  } // End of class ButtonListener

  private static void createAndShowGUI(){
     JFrame.setDefaultLookAndFeelDecorated(true);
     JFrame frame = new JFrame("Pipes and Filters Architecture demo- Legacy File Update");
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     PipeFilterTestGui newContentPane = new PipeFilterTestGui();
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