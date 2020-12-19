import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.Observable;
import java.util.Observer;


public class KelvinGUI extends JFrame implements Observer{
  private JSplitPane  bigSplitPane;
  private JPanel showInfoPane;
  private JPanel tempColorPane;
  private JLabel lblTempType;
  private JLabel lblColorTemp;
  private JTextArea tempTextArea;
  private JTextArea colorTempTxt;
  private static Dimension size = new Dimension(400, 200);

  public KelvinGUI() {
     super("Kelvin - Observer 3");
     setUpScrollPanes();
   }

  private void setUpScrollPanes() {
   	  Border raisedbevel = BorderFactory.createRaisedBevelBorder();

   	  lblTempType= new JLabel("Kelvin Temperature");
   	  lblTempType.setFont(new Font("Helvetica", Font.BOLD, 13));

   	  tempTextArea = new JTextArea(20, 20);
   	  tempTextArea.setFont(new Font("Helvetica", Font.BOLD, 20));
  	  tempTextArea.setLineWrap(true);
  	  tempTextArea.setBackground(Color.pink);

  	  showInfoPane = new JPanel();
  	  showInfoPane.setLayout(new BorderLayout());
  	  showInfoPane.add(lblTempType, "North");
  	  showInfoPane.add(tempTextArea, "Center");

      lblColorTemp = new JLabel("Temperature Color");
      lblColorTemp.setFont(new Font("Helvetica", Font.BOLD, 13));
  	  colorTempTxt = new JTextArea(20, 20);

  	  tempColorPane = new JPanel();
  	  tempColorPane.setLayout(new BorderLayout());
  	  tempColorPane.add(lblColorTemp, "North");
  	  tempColorPane.add(colorTempTxt, "Center");

  	  bigSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, showInfoPane,tempColorPane);
  	  bigSplitPane.setDividerLocation(200);

      getContentPane().add(bigSplitPane);
  	  setSize(size);
      setVisible(true);
   }

   public void update(Observable subject, Object arg) {

         //参数arg代表用户输入的锅炉温度数值
         String t = (String) arg;

         //参数subject代表被观察者，TemperatureGUI从这里传入
         //参见TestObserverObservable类
         TemperatureGUI tg = (TemperatureGUI)subject;
         String option = tg.getSelectedTemExpression();

         TemperatureConvertor tc = new TemperatureConvertor();
         float kTem = tc.getKelvinTemperature(option,t);
   	     tempTextArea.setText(""+ kTem);

   	     Color color=tc.getColor(kTem);
   	     colorTempTxt.setBackground(color);
   }
}

