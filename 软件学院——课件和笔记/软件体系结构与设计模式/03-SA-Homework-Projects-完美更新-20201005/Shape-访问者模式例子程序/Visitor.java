
public class Visitor{

    //访问三角形
    public void visitTriangle(Triangle t){ //访问三角形

    	double sa = t.getSideA();
    	double sb = t.getSideB();
    	double sc = t.getSideC();
    	//boolean flag = t.isAtriangle();
    	//if(flag == false){
		//	ClientGUI.dataTextArea.append("The 3 sides cannot formed a triangle"+ "\n");
		//}
		//else{
    	    ClientGUI.dataTextArea.append(t.describe()+"\n");
    	    ClientGUI.dataTextArea.append("Side A = " +sa + ", Side B = " + sb + ", Side C = " +sc + "\n");
	   // }

       // System.out.println("Side A = " +sa + ", Side B = " + sb + ", Side C = " +sc + "\n");

	}
    public void visitIsoscelesTriangle(IsoscelesTriangle i){ //访问等腰三角形
        double s = i.getSide();
	    double sb = i.getSideBottom();

	    ClientGUI.dataTextArea.append(i.describe()+"\n");
	    ClientGUI.dataTextArea.append("Slant side = " +s + ", Bottom side = " +sb+ "\n");
	}
    public void visitEquilateral(Equilateral e) {  //访问等边三角形
        double s = e.getSide();
        ClientGUI.dataTextArea.append(e.describe()+"\n");
	    ClientGUI.dataTextArea.append("side = " + s+ "\n");
	}
    public void visitRightTriangle(RightTriangle r){ //访问直角三角形
        double w = r.getWidth();
        double h = r.getHeight();
        ClientGUI.dataTextArea.append(r.describe()+"\n");
	    ClientGUI.dataTextArea.append("width = " + w + ", height = " + h+ "\n");
	}

	//访问四边形
	public void visitTrapezoid(Trapezoid t) {   //访问梯形 ？？？？？
	       double upper   = t.getSide1();
	       double bottom  = t.getSide3();
	       double slant1  = t.getSide2();
	       double slant2  = t.getSide4();

	       System.out.println("Upper side = " + upper +
		                                 ", Bottom side = " + bottom +
		                                  ", Slant side1 = " + slant1 +
		                                  ", Slant side2 = " + slant2 + "\n");

		   ClientGUI.dataTextArea.append(t.describe()+"\n");
		   ClientGUI.dataTextArea.append("Upper side = " + upper +
		                                 ", Bottom side = " + bottom +
		                                  ", Slant side1 = " + slant1 +
		                                  ", Slant side2 = " + slant2 + "\n");
	}
    public void visitParallelogram(Parallelogram p){ //访问平行四边形
       double bottom = p.getBottomSide();
	   double slantSide = p.getSlantSide();
	   double height = p.getHeight();

	   ClientGUI.dataTextArea.append(p.describe()+"\n");
	   ClientGUI.dataTextArea.append("Bottom = " + bottom + ", Slant side = " +
	                      slantSide+ ", Height = " + height+ "\n");
	}
    public void visitRectangle(Rectangle r){ //访问矩形

       double w = r.getWidth();
	   double h = r.getHeight();

	   ClientGUI.dataTextArea.append(r.describe()+"\n");
	   ClientGUI.dataTextArea.append("Width = " + w + ", Height = " + h+ "\n");

	}
    public void visitSquare(Square s) { //访问正方形

       double side = s.getSide();
       ClientGUI.dataTextArea.append(s.describe()+"\n");
	   ClientGUI.dataTextArea.append("side = " + side+ "\n");

	}
    //public void visitTrapezoid(Trapezoid t) { //访问梯形

	//}

	//访问椭圆
    public void visitEllipse(Ellipse e) { //访问椭圆形

        double cx = e.getCenterX();
	    double cy = e.getCenterY();
	    double rx = e.getXRadius();
	    double ry = e.getYRadius();
	    ClientGUI.dataTextArea.append(e.describe()+"\n");
	    ClientGUI.dataTextArea.append("CenterX = " + cx +
	                       ", CenterY = " + cy +
	                       ", RadiusX = " + rx +
	                       ", RadiusY = " + ry+ "\n" );
	}
    public void visitCircle(Circle c) { //访问圆形

        double cx = c.getCenterX();
	    double cy = c.getCenterY();
	    double r= c.getRadius();
	    ClientGUI.dataTextArea.append(c.describe()+"\n");
	    ClientGUI.dataTextArea.append("CenterX = " + cx +
			               ", CenterY = " + cy +
			               ", Radius = " + r+ "\n" );
	}
}