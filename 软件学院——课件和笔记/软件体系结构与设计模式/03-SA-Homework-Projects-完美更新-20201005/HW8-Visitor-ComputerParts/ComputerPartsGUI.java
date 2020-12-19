import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

/*============================================================*/
/* The User interface for testing the visitor design pattern  */
/*============================================================*/

public class ComputerPartsGUI extends JFrame implements ItemListener{
  private JScrollPane checkBoxPane, dataPane, textPane;
  private JSplitPane  upSplitPane, bigSplitPane;
  private JTextArea   txtAreaPrice, txtAreaData;
  private JButton submitBtn, exitBtn;
  private JPanel checkBoxPanel, btnPanel, choicePanel;
  String[] compParts={ "Case","Motherboard","Microprocessor","Memory",
                       "DriveController","VideoCard","Fan","PowerSupply",
                       "HardDiskDrive","CDDrive","DVDDevice","Monitor",
                       "Keyboard","Mouse","Assembly","WholePC"};
  private JCheckBox[] cParts;
  public final int SELECTED = ItemEvent.SELECTED;
  public final int DESELECTED = ItemEvent.DESELECTED;
  private Dimension   minimumSize;
  private GridBagLayout gridbag = new GridBagLayout();
  private GridBagConstraints gbc = new GridBagConstraints();
  private int[] states;

  public ComputerPartsGUI(){
      super("Visitor Pattern - Computer Parts");
      minimumSize = new Dimension(130, 100);
      states = new int[30];
      setUpChoicePanel();
      setUpScrollPanes();
  }
  private void setUpChoicePanel(){
      submitBtn = new JButton("Submit");
      exitBtn = new JButton("Exit");
      submitBtn.addActionListener( new ButtonActionListener());
      exitBtn.addActionListener( new ButtonActionListener());

      JPanel btnPanel =new JPanel();
      btnPanel.add(submitBtn);
      btnPanel.add(exitBtn);

      cParts=new JCheckBox[compParts.length];
      for(int m=0; m<compParts.length; m++){
          cParts[m] = new JCheckBox(compParts[m]);
          cParts[m].setMnemonic(KeyEvent.VK_C);
          cParts[m].addItemListener(this);
      }

      checkBoxPanel = new JPanel();
      checkBoxPanel.setLayout(gridbag);
      for(int m=0; m<compParts.length; m++){
		  checkBoxPanel.add(cParts[m]);
      }
      gbc.insets.top = 0;
      gbc.insets.bottom = 0;
      gbc.insets.left = 0;
      gbc.insets.right = 0;
      gbc.anchor = GridBagConstraints.WEST;
      add(0, 0, cParts[0]);
      add(1, 1, cParts[1]);
      for(int k=2; k<7; k++)
         add(2, k, cParts[k]);
      for(int k=7; k<11; k++)
         add(1, k, cParts[k]);
      for(int k=11; k<16; k++)
         add(0, k, cParts[k]);

      choicePanel = new JPanel();
      choicePanel.setMinimumSize(new Dimension(550, 300));
      choicePanel.setLayout(new BorderLayout());
      choicePanel.add(checkBoxPanel, "Center");
      choicePanel.add(btnPanel, "South");
  }
  private void add(int m, int n, JComponent com ){
  	 gbc.gridx = m;
  	 gbc.gridy = n;
     gridbag.setConstraints(com, gbc);
  }

   // All the scroll panes are set up
   private void setUpScrollPanes(){
	   txtAreaPrice = new JTextArea(3,10);
	   txtAreaData = new JTextArea(3,10);
       dataPane = new JScrollPane(txtAreaData);
       dataPane.setMinimumSize(minimumSize);
       checkBoxPane = new JScrollPane(choicePanel);
  	   upSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
  	   upSplitPane.setLeftComponent(checkBoxPane);
  	   upSplitPane.setRightComponent(dataPane);
  	   textPane = new JScrollPane(txtAreaPrice);
       textPane.setMinimumSize(new Dimension(100, 100));
  	   upSplitPane.setDividerLocation(350);
  	   upSplitPane.setPreferredSize(new Dimension(550, 200));
  	   bigSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, upSplitPane, textPane);
  	   bigSplitPane.setDividerLocation(500);

       getContentPane().add(bigSplitPane);
  	   setSize(new Dimension(550, 300));
       setVisible(true);
    }

  //========================================
  // �����ӹ��ܣ���Ҫ���Ȿ���������漰�ķ�����
  //========================================
  class ButtonActionListener implements ActionListener{
       public void actionPerformed(ActionEvent e) {
		   txtAreaPrice.append("\n=======New Order ========\n");
		   createPartObjAndVisitParts(e);
       }
  }
  public void itemStateChanged(ItemEvent e){
       Object source = e.getItemSelectable();
	   int state = e.getStateChange();

	    if (source == cParts[0]) {
			if(state == SELECTED){
			   cParts[1].setSelected(true);
			   for(int k=7; k<11; k++)
			      cParts[k].setSelected(true);
		    }
		    else if (state == DESELECTED){
			   cParts[1].setSelected(false);
			   for(int k=7; k<11; k++)
			      cParts[k].setSelected(false);
		    }
			states[0]=state;
        }
        else if (source ==  cParts[1]) {
			if(state == SELECTED){
			   for(int k=2; k<7; k++)
			      cParts[k].setSelected(true);
		    }
		    else if (state == DESELECTED){
			   for(int k=2; k<7; k++)
			      cParts[k].setSelected(false);
		    }
            states[1]=state;
        }
        else if (source == cParts[2])
			states[2]=state;
        else if (source == cParts[3])
			states[3]=state;
        else if (source == cParts[4])
			states[4]=state;
		else if (source == cParts[5])
			states[5]=state;
        else if (source == cParts[6])
		    states[6]=state;
        else if (source == cParts[7])
		    states[7]=state;
		else if (source == cParts[8])
			states[8]=state;
        else if (source == cParts[9])
			states[9]=state;
        else if (source == cParts[10])
			states[10]=state;
        else if (source == cParts[11])
			states[11]=state;
        else if (source == cParts[12])
			states[12]=state;
        else if (source == cParts[13])
			states[13] = state;
		else if (source == cParts[14]){
		   if(state == SELECTED){
			   cParts[1].setSelected(true);
			   cParts[8].setSelected(true);
		    }
		    else if (state == DESELECTED){
			   cParts[1].setSelected(false);
			   cParts[8].setSelected(false);
		    }
			states[14]=state;
		}
		else if (source == cParts[15]){
		    if(state == SELECTED){
			   cParts[0].setSelected(true);
			   for(int k=11; k<14; k++)
			      cParts[k].setSelected(true);
			}
			else if (state == DESELECTED){
			   cParts[0].setSelected(false);
			   for(int k=11; k<14; k++)
			      cParts[k].setSelected(false);
		    }
		    states[15]=state;
		}
  }
  private void createPartObjAndVisitParts(ActionEvent e){
	  ComputerPart part = null;
	  PriceVisitor pv = new PriceVisitor();
     PartsInfoVisitor iv = new PartsInfoVisitor();
     KnowledgeVisitor kv = new KnowledgeVisitor();

	  CompositeStructure comStruct = new CompositeStructure();
	  int len = states.length;
	  ArrayList<String> msg = new ArrayList<String>();

	  if (e.getActionCommand().equals("Submit")) {
		 for(int m = 0; m < len; m++ ){
            if ((m==0) && (states[0] == SELECTED)) {
  	           part = new ComputerCase();
  	           msg.add("Computer case ");
  	        }
  	        else if ((m==1) && (states[1] == SELECTED)) {
			   part = new Motherboard();
			   msg.add("Motherboard ");
  	        }
  	        else if ((m==2) && (states[2] == SELECTED)) {
			   part = new Microprocessor();
			   msg.add("Microprocessor");
			}
			else if ((m==3) && (states[3] == SELECTED)){
			   part = new Memory();
			   msg.add("Memory ");
			}
			else if ((m==4) && (states[4] == SELECTED)){
			   part = new DriveController();
			   msg.add("Drive Controller ");
            }
            else if ((m==5) && (states[5] == SELECTED)){
			   part = new VideoCard();
			   msg.add("Video Card ");
			}
			else if ((m==6) && (states[6] == SELECTED)){
			   part = new Fan();
			   msg.add("Fan ");
			}
            else if ((m==7) && (states[7] == SELECTED)) {
               part = new PowerSupply();
               msg.add("Power supply ");
            }
  	        else if ((m==8) && (states[8] == SELECTED)){
  		       part = new HardDiskDrive();
  		       msg.add("HardDiskDrive ");
            }
            else if ((m==9) && (states[9] == SELECTED)){
			   part = new CDDrive();
			   msg.add("CDDrive ");
            }
            else if ((m==10) && (states[10] == SELECTED)) {
			   part = new DVDDevice();
			   msg.add("DVDDevice ");
			}
  	        else if ((m==11) && (states[11] == SELECTED)) {
  		       part = new Monitor();
  		       msg.add("Monitor ");
  	        }
  	        else if ((m==12) && (states[12] == SELECTED)) {
  	   	       part = new Keyboard();
  	   	       msg.add("Keyboard ");
  	        }
  	        else if  ((m==13) && (states[13] == SELECTED)) {
  		       part = new Mouse();
  		       msg.add("Mouse ");
  	        }
            // states[14] and states[15] will not do things independently

            if(part != null){
			    comStruct.attach(part);
			    part = null;
            }
         }//end for loop
         comStruct.accept(pv);
         comStruct.accept(iv);
         comStruct.accept(kv);
         ArrayList<Double> prices = pv.getPartsPrices();

         //Display parts names and prices in pairs
         for(int m=0; m<prices.size(); m++){
             txtAreaPrice.append(msg.get(m)+ "Price: " + prices.get(m).doubleValue() + "\n");
		 }
         txtAreaPrice.append("\n Total Price: " + pv.getPriceTotal());
         txtAreaData.append("\n ========New Order========" );
         txtAreaData.append("\n Parts descriptions: " + iv.printAllOrderInfo()+"\n");
         txtAreaData.append("\n Knowledge descriptions: "+kv.printAllOrderInfo()+"\n");
	  }
      else if (e.getActionCommand().equals("Exit")) {
  		   System.exit(1);
  	  }
    }

  public static void main(String args[]){
    try {
		 JFrame.setDefaultLookAndFeelDecorated(true);
        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    }
    catch (Exception evt) {}

    ComputerPartsGUI frame = new ComputerPartsGUI();
    frame.addWindowListener(new WindowAdapter(){
      public void windowClosing(WindowEvent e){
        System.exit(0);
      }
    });
    frame.setSize(550, 650);
    frame.setVisible(true);
  }
}

