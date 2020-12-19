import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;


public class ClientGUI extends JPanel{

   private JComboBox cmbFileList;
   private JButton srchButton;
   private JButton resetButton;
   private JButton exitButton;
   private JPanel buttonPanel;
   private JScrollPane btnPane;
   private JTextArea[] txtArea;

   private JTextArea filterInfoTxt;
   private JScrollPane filterInfoPane;
   private JSplitPane upSplitPane;

   private JScrollPane[] txtPane;
   private JPanel downPanel;
   private JSplitPane bigSplitPane;

   private ArrayList<String> ftrs;
   private JLabel imlbl, filterlbl;
   private JLabel[] imgLabel;

   static final String PROCESS = "Process";
   static final String RESET = "Reset";
   static final String EXIT = "Exit";
   static final String POPULATIONFILES = "PopulationFiles/";
   static final Dimension minimumSize = new Dimension(230, 200);
   static final int SELECTED = ItemEvent.SELECTED;
   static final int DESELECTED = ItemEvent.DESELECTED;

   public ClientGUI(){
      super(new GridLayout(1,0));
      ftrs = new ArrayList<String>();
	  filterInfoTxt=new JTextArea(6, 20);

	  setUpButtonPanel();
      buildUpScrollGUI();
   }

   private void buildUpScrollGUI(){
      txtArea = new JTextArea[5];
	  txtPane = new JScrollPane[5];

	  for(int m=0;m<5; m++){
	     txtArea[m] = new JTextArea();
	  	 txtArea[m].setMinimumSize(new Dimension(250, 300));
	  	 txtPane[m] = new JScrollPane(txtArea[m]);
	  }

	  btnPane = new JScrollPane(buttonPanel);
	  btnPane.setMinimumSize(minimumSize);
	  filterInfoPane = new JScrollPane(filterInfoTxt);

	  upSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	  upSplitPane.setDividerLocation(400);
	  upSplitPane.setPreferredSize(new Dimension(800, 350));
	  upSplitPane.setLeftComponent(btnPane);
	  upSplitPane.setRightComponent(filterInfoPane);

	  downPanel = new JPanel();
	  downPanel.setLayout(new GridLayout(1,0));
	  downPanel.add(txtPane[0]);
	  downPanel.add(txtPane[1]);
	  downPanel.add(txtPane[2]);
	  downPanel.add(txtPane[3]);
	  downPanel.add(txtPane[4]);

	  bigSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, upSplitPane, downPanel);
	  bigSplitPane.setDividerLocation(190);

	  add(bigSplitPane);
	  setSize(new Dimension(800, 500));
      setVisible(true);
  }

  private void setUpButtonPanel(){

	 cmbFileList = new JComboBox();
	 String[] cl = extractFileList();
     setupFileList(cmbFileList,cl);

     imlbl = new JLabel("Images:");

     //Create buttons
     srchButton = new JButton(PROCESS);
     srchButton.setMnemonic(KeyEvent.VK_S);
     resetButton = new JButton(RESET);
     resetButton.setMnemonic(KeyEvent.VK_S);

     exitButton = new JButton(EXIT);
     exitButton.setMnemonic(KeyEvent.VK_X);

     ButnListener objButtonHandler = new ButnListener();

     // add action Listener
     srchButton.addActionListener(objButtonHandler);
     resetButton.addActionListener(objButtonHandler);
     exitButton.addActionListener(objButtonHandler);

     buttonPanel = new JPanel();

     GridBagLayout gridbag = new GridBagLayout();
     buttonPanel.setLayout(gridbag);
     GridBagConstraints gbc = new GridBagConstraints();

     buttonPanel.add(imlbl);
     buttonPanel.add(cmbFileList);
     buttonPanel.add(srchButton);
     buttonPanel.add(resetButton);
     buttonPanel.add(exitButton);

     gbc.insets.top = 5;
	 gbc.insets.bottom = 5;
	 gbc.insets.left = 5;
     gbc.insets.right = 5;

     gbc.anchor = GridBagConstraints.EAST;

     gbc.gridx = 0;
     gbc.gridy = 0;
     gridbag.setConstraints(imlbl, gbc);
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
     gbc.gridy = 9;
     gridbag.setConstraints(srchButton, gbc);
     gbc.anchor = GridBagConstraints.WEST;
     gbc.gridx = 1;
     gbc.gridy = 9;
     gridbag.setConstraints(resetButton, gbc);
     gbc.anchor = GridBagConstraints.WEST;
     gbc.gridx = 2;
	 gbc.gridy = 9;
     gridbag.setConstraints(exitButton, gbc);
     gbc.anchor = GridBagConstraints.WEST;
  }

  /*===========================================*/
  /* Get a selected file name from user input   */
  /*===========================================*/
  public String getSelectedFile() {
     return (String) cmbFileList.getSelectedItem();
  }

  /*================================================*/
  /* Extract legacy file names from a diectory on   */
  /* your computer                                  */
  /*================================================*/
  public String[] extractFileList(){
     File f = new File(POPULATIONFILES);
     String [] fileNames = f.list();
     return fileNames;
  }

  /*=======================================*/
  /* Add file list to combox cmbCarList.   */
  /*=======================================*/
  public void setupFileList(JComboBox cmbFileList,String[] fileList){
     for(int k=0; k<fileList.length; k++){
        cmbFileList.addItem(fileList[k]);
     }
  }

  class ButnListener implements ActionListener{
      public void actionPerformed(ActionEvent e){

        if (e.getActionCommand().equals(ClientGUI.EXIT)){
           System.exit(1);
        }
        if (e.getActionCommand().equals(ClientGUI.RESET)){
		   for(int m=0;m<5; m++){
		      txtArea[m].setText("");
		   }
        }

        if (e.getActionCommand().equals(ClientGUI.PROCESS)){

           for(int m=0;m<5; m++){
	          txtArea[m].setText("");
		   }

           try{
			  String selectedFile = getSelectedFile();
              PipeLineBuilder pipeline = new PipeLineBuilder(ftrs, txtArea, filterInfoTxt);
              pipeline.buildAndRunPipeFilters(selectedFile);
	       }
	       catch(IOException exc){
              exc.printStackTrace();
              System.exit(1);
           }
       }
     }
  } // End of class ButnListener

  private static void createAndShowGUI(){
     JFrame.setDefaultLookAndFeelDecorated(true);
     JFrame frame = new JFrame("Batch Sequential Software Architecture-Legacy File Update");
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