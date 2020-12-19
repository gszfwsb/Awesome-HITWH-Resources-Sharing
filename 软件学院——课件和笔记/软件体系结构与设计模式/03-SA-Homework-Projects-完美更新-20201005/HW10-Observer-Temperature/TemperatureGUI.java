import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import com.sun.java.swing.plaf.windows.*;
import java.util.Observable;
import java.util.Observer;

public class TemperatureGUI extends Observable{
   private JFrame frame= new JFrame("TemperatureGUI - Observable");
   private String grassState;
   private JTextField boilerTem;
   private String bTem;
   public static final String SUBMIT = "Submit";
   public static final String EXIT = "Exit";

   public static final String CELSIUS = "Celsius";
   public static final String FAHRENHEIT = "Fahrenheit";
   public static final String KELVIN = "Kelvin";
   public static final String RANKINE = "Rankine";
   public static final String REAUMUR = "Reaumur";



   private JComboBox cmBoxTemperatures;

   public TemperatureGUI() throws Exception{
      JButton btnOK = new JButton(SUBMIT);
      btnOK.addActionListener(new ButtonHandler());

      JButton btnEixt = new JButton(EXIT);
      btnEixt.addActionListener(new ButtonHandler());

      cmBoxTemperatures = new JComboBox();
      cmBoxTemperatures.addItem(CELSIUS);
      cmBoxTemperatures.addItem(FAHRENHEIT);
      cmBoxTemperatures.addItem(KELVIN);

      JPanel btnPanel = new JPanel();
      GridBagLayout gridbag = new GridBagLayout();
      btnPanel.setLayout(gridbag);
      GridBagConstraints gbc = new GridBagConstraints();

      JLabel lblTemperature = new JLabel("Boiler Temperature: ");
      boilerTem = new JTextField(20);

      JLabel lblTempOptions = new JLabel("Temperature options: ");

      btnPanel.add(lblTempOptions);
      btnPanel.add(cmBoxTemperatures);

      btnPanel.add(lblTemperature);
      btnPanel.add(boilerTem);
      btnPanel.add(btnOK);
      btnPanel.add(btnEixt);

      gbc.gridx = 0;
	  gbc.gridy = 0;
	  gridbag.setConstraints(lblTempOptions, gbc);
	  gbc.gridx = 1;
	  gbc.gridy = 0;
      gridbag.setConstraints(cmBoxTemperatures, gbc);

      gbc.gridx = 0;
	  gbc.gridy = 1;
	  gridbag.setConstraints(lblTemperature, gbc);
	  gbc.gridx = 1;
	  gbc.gridy = 1;
      gridbag.setConstraints(boilerTem, gbc);

      gbc.insets.top = 30;
      gbc.gridx = 1;
      gbc.gridy = 3;
      gridbag.setConstraints(btnOK, gbc);

      gbc.gridx = 2;
	  gbc.gridy = 3;
      gridbag.setConstraints(btnEixt, gbc);

      Container contentPane = frame.getContentPane();

      contentPane.add(btnPanel, BorderLayout.CENTER);
      try {
         UIManager.setLookAndFeel(new WindowsLookAndFeel());
         SwingUtilities.updateComponentTreeUI(frame);
      }
      catch (Exception ex) {
         System.out.println(ex);
      }

      frame.setSize(360, 160);
      frame.setVisible(true);
   }


   public void setTemperature(String tem){
         bTem = tem;
   }

   public String getSelectedTemExpression(){
      String option = (String)cmBoxTemperatures.getSelectedItem();
      return option;
   }

   class ButtonHandler implements ActionListener{
      public void actionPerformed(ActionEvent e){
         if (e.getActionCommand().equals(SUBMIT)){

            String tem = boilerTem.getText();

            if(tem.compareTo("") == 0){
			   System.out.println("Need to enter temperature.");
			}
            else{
               setTemperature(tem);
               setChanged();
               notifyObservers(bTem);
               //就是因为有了参数bTem，所以在所有的观察者的
               //update(Observable subject, Object arg) 方法
               //的参数arg都代表用户输入的锅炉温度
		    }
         }
         else if(e.getActionCommand().equals(EXIT)){
			System.exit(1);

		 }
    }
  }
}// end of class

