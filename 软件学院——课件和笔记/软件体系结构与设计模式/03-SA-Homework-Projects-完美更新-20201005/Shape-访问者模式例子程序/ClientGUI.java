import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class ClientGUI extends JPanel{

   private JSplitPane splitPane;
   private JScrollPane btnPane,textPane;
   private JPanel UIPanel;
   private JTextField txtTriangle, txtQuadrilateral,txtEllipse;
   public static final String SUBMIT = "Submit";
   public static final String EXIT = "Exit";

   private static final String TRIANGLE = "任意三角形";
   private static final String ISOTRIANGLE = "等腰三角形";
   private static final String EQUILATERAL = "等边三角形";
   private static final String RIGHTTRIANGLE = "直角三角形";
   private static final String PARALLELOGRAM = "平行四边形";
   private static final String RECTANGLE = "矩形";
   private static final String SQUARE = "正方形";
   private static final String TRAPEZOID = "梯形";

   private static final String ELLIPSE = "椭圆";
   private static final String CIRCLE = "圆形";
   private JComboBox cmboxTriangle, cmboxQuadrilateral, cmboxEllipse;

   public static JTextArea dataTextArea = new JTextArea(6, 10);


   public ClientGUI(){
	  super(new GridLayout(1,0));
      buildUpScrollGUI();
   }

   private void buildUpScrollGUI(){
	  setUpButtonPanel();

      //dataTextArea = new JTextArea(6, 10);
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
      txtTriangle = new JTextField(20);
	  txtQuadrilateral = new JTextField(20);
	  txtEllipse = new JTextField(20);
	  cmboxTriangle = new JComboBox();
	  cmboxTriangle.addItem(TRIANGLE);
	  cmboxTriangle.addItem(ISOTRIANGLE);
	  cmboxTriangle.addItem(EQUILATERAL);
	  cmboxTriangle.addItem(RIGHTTRIANGLE);

	  cmboxQuadrilateral = new JComboBox();
	  cmboxQuadrilateral.addItem(PARALLELOGRAM);
	  cmboxQuadrilateral.addItem(RECTANGLE);
	  cmboxQuadrilateral.addItem(SQUARE);
	  cmboxQuadrilateral.addItem(TRAPEZOID);

	  cmboxEllipse = new JComboBox();
	  cmboxEllipse.addItem(ELLIPSE);
	  cmboxEllipse.addItem(CIRCLE);

	  //Create the open button
	  JButton validateButton = new JButton(SUBMIT);
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

	  UIPanel.add(cmboxTriangle);
	  UIPanel.add(txtTriangle);
	  UIPanel.add(cmboxQuadrilateral);
	  UIPanel.add(txtQuadrilateral);
	  UIPanel.add(cmboxEllipse);
	  UIPanel.add(txtEllipse);
	  UIPanel.add(validateButton);
	  UIPanel.add(exitButton);

	  gbc.insets.top = 5;
	  gbc.insets.bottom = 5;
	  gbc.insets.left = 5;
	  gbc.insets.right = 5;
	  gbc.anchor = GridBagConstraints.WEST;

	  gbc.gridx = 0;
	  gbc.gridy = 0;
	  gridbag.setConstraints(cmboxTriangle, gbc);
	  gbc.gridx = 1;
	  gbc.gridy = 0;
	  gridbag.setConstraints(txtTriangle, gbc);
	  gbc.gridx = 0;
	  gbc.gridy = 1;
	  gridbag.setConstraints(cmboxQuadrilateral, gbc);
	  gbc.gridx = 1;
	  gbc.gridy = 1;
	  gridbag.setConstraints(txtQuadrilateral, gbc);

	  gbc.gridx = 0;
	  gbc.gridy = 2;
	  gridbag.setConstraints(cmboxEllipse, gbc);
	  gbc.gridx = 1;
	  gbc.gridy = 2;
	  gridbag.setConstraints(txtEllipse, gbc);

	  gbc.insets.left = 2;
	  gbc.insets.right = 2;
	  gbc.insets.top = 40;

	  JPanel buttonPanel = new JPanel();
	  buttonPanel.add(validateButton);
	  buttonPanel.add(exitButton);
	  UIPanel.add(buttonPanel);
	  gbc.gridx = 1;
	  gbc.gridy = 6;
	  gridbag.setConstraints(buttonPanel, gbc);
    }

    public String getATriangle() {
        return (String) cmboxTriangle.getSelectedItem();
    }
    public String getAQuadrilateral() {
	    return (String) cmboxQuadrilateral.getSelectedItem();
    }
    public String getAnEllipse() {
		return (String) cmboxEllipse.getSelectedItem();
    }


	public double[] getTriangleData(){

		String strData = txtTriangle.getText();
		String[] strDataArr = strData.split(" ");
		double[] doubleArray = new double[strDataArr.length];

		for(int i=0;i<strDataArr.length;i++){
			//doubleArray[i] = Double.valueOf(strDataArr[i]);
			doubleArray[i] = Double.parseDouble(strDataArr[i]);
			//Double.parseDouble("920.239")

			System.out.println(doubleArray[i]);
	    }
	    return doubleArray;
	 }

	 public double[] getQuadrilateralData(){

		String strData =  txtQuadrilateral.getText();
		String[] strDataArr = strData.split(" ");
		double[] doubleArray = new double[strDataArr.length];

		for(int i=0;i<strDataArr.length;i++){
			doubleArray[i] = Double.parseDouble(strDataArr[i]);
			System.out.println(doubleArray[i]);
		}
	    return doubleArray;
	 }

	 public double[] getEllipseData(){
		 String strData = txtEllipse.getText();
		 String[] strDataArr = strData.split(" ");
		 double[] doubleArray = new double[strDataArr.length];

		 for(int i=0;i<strDataArr.length;i++){
		 	doubleArray[i] = Double.parseDouble(strDataArr[i]);
		 	System.out.println(doubleArray[i]);
		 }
	     return doubleArray;
	 }

    class ButtonListener implements ActionListener{
      // CusInfoValidator cusInfo = new InformationAdapter();
       Visitor v = new Visitor();
       Visitor2 v2 = new Visitor2();

       public void actionPerformed(ActionEvent e){

          if (e.getActionCommand().equals(EXIT)){
             System.exit(1);
          }
          if (e.getActionCommand().equals(SUBMIT)){
             double[] triData = getTriangleData();
			 double[] quadData = getQuadrilateralData();
			 double[] ellipData = getEllipseData();

			 String aTriangle = getATriangle();
			 String aQuad = getAQuadrilateral();
			 String anEllipse = getAnEllipse();

             if(aTriangle.compareTo(TRIANGLE)==0 ){ //任意三角形
			     Triangle triangle = new Triangle(triData[0], triData[1], triData[2]);
			     boolean flag = triangle.isATriangle();
			     if(flag == false){
					 dataTextArea.append("The 3 sides cannot formed a triangle"+ "\n\n");
				 }
				 else{
			         triangle.accept(v);
			         triangle.accept(v2);
			     }
			 }
             if(aTriangle.compareTo(ISOTRIANGLE)==0 ){ //等腰三角形
                 Shape isosceles = new IsoscelesTriangle(triData[0], triData[1]);
                 isosceles.accept(v);
                 isosceles.accept(v2);
			 }
			 else if(aTriangle.compareTo(EQUILATERAL)==0 ){ //等边三角形
			     Shape equilat = new Equilateral(triData[0]);
			     equilat.accept(v);
			     equilat.accept(v2);
			 }
			 else if(aTriangle.compareTo(RIGHTTRIANGLE)==0 ){ //直角三角形
			     Shape right = new RightTriangle(triData[0],triData[1]);
			     right.accept(v);
			     right.accept(v2);
			 }

			 if(aQuad.compareTo(PARALLELOGRAM)==0 ){ //平行四边形
			 	 Shape para = new Parallelogram(quadData[0], quadData[1], quadData[2]);
			 	 para.accept(v);
			 	 para.accept(v2);
			 }
			 else if(aQuad.compareTo(RECTANGLE)==0 ){ //矩形
			 	 Shape rect = new Rectangle(quadData[0],quadData[1]);
			 	 rect.accept(v);
			 	 rect.accept(v2);
			 }
			 else if(aQuad.compareTo(SQUARE)==0 ){ //正方形
			 	 Shape sq = new Square(quadData[0]);
			 	 sq.accept(v);
			 	 sq.accept(v2);
			 }
			 else if(aQuad.compareTo(TRAPEZOID)==0 ){ //梯形
			 	 Shape tr = new Trapezoid(quadData[0],quadData[1], quadData[2], quadData[3],
			 	                          quadData[4], quadData[5], quadData[6], quadData[7]);
			 	 tr.accept(v);
			 	 tr.accept(v2);
			 }

			 if(anEllipse.compareTo(ELLIPSE)==0 ){ //椭圆
			 	 Shape ellipse = new Ellipse(ellipData[0], ellipData[1], ellipData[2], ellipData[3]);
			 	 ellipse.accept(v);
			 	 ellipse.accept(v2);
			 }
			 else if(anEllipse.compareTo(CIRCLE)==0 ){ //圆
			 	 Shape circle = new Circle(ellipData[0], ellipData[1], ellipData[2]);
			 	 circle.accept(v);
			 	 circle.accept(v2);
			 }

			 //else{
			 //   dataTextArea.append("\nCorrect format of SSN.");
			 //} */
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