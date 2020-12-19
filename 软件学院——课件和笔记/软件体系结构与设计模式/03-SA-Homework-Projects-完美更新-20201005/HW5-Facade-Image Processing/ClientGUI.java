import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class ClientGUI extends JPanel{

   private JComboBox cmbImageList;
   private JCheckBox blur,edge, sharp;

   private JButton srchButton, resetButton, exitButton;
   private JPanel buttonPanel;
   private JScrollPane btnPane;

   private JTextArea filterInfoTxt;
   private JScrollPane filterInfoPane;
   private JSplitPane upSplitPane;

   private JScrollPane[] imagePane;
   private JPanel downPanel;
   private JSplitPane bigSplitPane;

   private ArrayList<String> ftrs;
   private JLabel imlbl, filterlbl;
   private JLabel[] imgLabel;

   static final String PROCESS = "Process";
   static final String RESET = "Reset";
   static final String EXIT = "Exit";
   static final String IMAGES = "Images/";
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
      imgLabel = new JLabel[4];
      imagePane = new JScrollPane[4];

	  for(int m=0;m<4; m++){
	     imgLabel[m] = new JLabel();
	     imgLabel[m].setMinimumSize(new Dimension(250, 300));
	     imagePane[m] = new JScrollPane(imgLabel[m]);
	  }

	  btnPane = new JScrollPane(buttonPanel);
	  btnPane.setMinimumSize(minimumSize);
	  filterInfoPane = new JScrollPane(filterInfoTxt);

	  upSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	  upSplitPane.setDividerLocation(420);
	  upSplitPane.setPreferredSize(new Dimension(800, 310));
	  upSplitPane.setLeftComponent(btnPane);
	  upSplitPane.setRightComponent(filterInfoPane);

	  downPanel = new JPanel();
	  downPanel.setLayout(new GridLayout(1,0));
	  downPanel.add(imagePane[0]);
	  downPanel.add(imagePane[1]);
	  downPanel.add(imagePane[2]);
	  downPanel.add(imagePane[3]);

	  bigSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, upSplitPane, downPanel);
	  bigSplitPane.setDividerLocation(180);


	  add(bigSplitPane);
	  setSize(new Dimension(800, 550));
      setVisible(true);
  }

  private void setUpButtonPanel(){

	 cmbImageList = new JComboBox();
	 String[] cl = extractImageList();
     setupImageList(cmbImageList,cl);

     imlbl = new JLabel("Images:");
     filterlbl = new JLabel("Choose 3 Filters:");

     blur  = new JCheckBox("Blur Filter");
     sharp  = new JCheckBox("Sharpen Filter");
     //edge  = new JCheckBox("EdgeDetectionFilter");

     CheckboxListener listener = new CheckboxListener();

     blur.addItemListener(listener);
     sharp.addItemListener(listener);
     //edge.addItemListener(listener);

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
     buttonPanel.add(cmbImageList);
     buttonPanel.add(filterlbl);

     buttonPanel.add(blur);
	 buttonPanel.add(sharp);
     //buttonPanel.add(edge);

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
     gridbag.setConstraints(cmbImageList, gbc);
     gbc.anchor = GridBagConstraints.EAST;
     gbc.gridx = 0;
	 gbc.gridy = 1;
	 gridbag.setConstraints(filterlbl, gbc);
	 gbc.anchor = GridBagConstraints.WEST;
	 gbc.gridx = 1;
	 gbc.gridy = 1;
	 gridbag.setConstraints(blur, gbc);
     gbc.anchor = GridBagConstraints.WEST;
     gbc.gridx = 1;
	 gbc.gridy = 3;
	 gridbag.setConstraints(sharp, gbc);
     gbc.anchor = GridBagConstraints.WEST;
     //gbc.gridx = 1;
	 //gbc.gridy = 4;
	 //gridbag.setConstraints(edge, gbc);
     //gbc.anchor = GridBagConstraints.WEST;

     gbc.gridx = 0;
     gbc.gridy = 1;
     gbc.insets.left = 2;
     gbc.insets.right = 2;
     gbc.insets.top = 25;
     gbc.anchor = GridBagConstraints.EAST;

     gbc.gridx = 0;
     gbc.gridy = 7;
     gridbag.setConstraints(srchButton, gbc);
     gbc.anchor = GridBagConstraints.WEST;

     gbc.gridx = 1;
	 gbc.gridy = 7;
	 gridbag.setConstraints(resetButton, gbc);
     gbc.anchor = GridBagConstraints.WEST;

     gbc.gridx = 2;
     gbc.gridy = 7;
     gridbag.setConstraints(exitButton, gbc);
  }

  /*===========================================*/
  /* Get a selected image name from user input   */
  /*===========================================*/
  public String getSelectedImage() {
     return (String) cmbImageList.getSelectedItem();
  }

  /*================================================*/
  /* Extract image names from a diectory on your    */
  /* computer                                       */
  /*================================================*/
  public String[] extractImageList(){
     File f = new File(IMAGES);
     String [] fileNames = f.list();
     return fileNames;
  }

  /*==============================================*/
  /* Add image list to combox cmbCarList. Both      */
  /* objects imgList and cmbImageList are passed    */
  /* in from parameters.                          */
  /*==============================================*/
  public void setupImageList(JComboBox cmbImageList,String[] imgList){
     for(int k=0; k<imgList.length; k++){
        cmbImageList.addItem(imgList[k]);
     }
  }

  /*------------------------------------------------------*/
  /* This listener is used for adding a filter name to or */
  /* deleting a filter name from a filter list, which is  */
  /* implemented as an ArrayList                          */
  /*------------------------------------------------------*/
  class CheckboxListener implements ItemListener {

     public void itemStateChanged(ItemEvent e){
        Object source = e.getItemSelectable();
  	    int state = e.getStateChange();
  	    System.out.println("state" + " = "+state);

 	    if (source == blur) {
		   if(state==SELECTED)
		      ftrs.add("BlurFilter");
		   else if(state==DESELECTED)
		      ftrs.remove("BlurFilter");
		}
		else if (source == sharp) {
		   if(state == SELECTED)
		      ftrs.add("SharpenFilter");
		   else if(state == DESELECTED)
		      ftrs.remove("SharpenFilter");
		}
		//You need to add code here

	 }
  }

  class ButnListener implements ActionListener{
      public void actionPerformed(ActionEvent e){

        if (e.getActionCommand().equals(ClientGUI.EXIT)){
           System.exit(1);
        }
        if (e.getActionCommand().equals(ClientGUI.RESET)){
		   clearLabels();
        }
        if (e.getActionCommand().equals(ClientGUI.PROCESS)){
           clearLabels();
           String selectedImg = getSelectedImage();
           String originImg = IMAGES + selectedImg;

           try{
			  // Create an object of class ImageProcFacade,
			  // and call its method processImage(selectedImg)
		      ImageProcFacade facade = new ImageProcFacade(ftrs, imgLabel, filterInfoTxt);
		      facade.processImage(selectedImg);
		   }
		   catch (IOException e1){
		      e1.printStackTrace();
   	       }
       }
     }
  } // End of class ButnListener

  private static void createAndShowGUI(){
     JFrame.setDefaultLookAndFeelDecorated(true);
     JFrame frame = new JFrame("Facade pattern-Image Processing example");
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     ClientGUI newContentPane = new ClientGUI();
     newContentPane.setOpaque(true);
     frame.setContentPane(newContentPane);

     frame.pack();
     frame.setVisible(true);
  }

  private void clearLabels(){
     for(int i = 0; i< imgLabel.length; i++){
	    imgLabel[i].setIcon(null);
	 }
  }

  static public void main(String argv[]) {
	 javax.swing.SwingUtilities.invokeLater(new Runnable() {
	    public void run() {
		   createAndShowGUI();
		}
        });
  }
}