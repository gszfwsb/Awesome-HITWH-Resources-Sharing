
public class Visitor2{

     //共同接口访问者
     public void visit(Shape s){

        //if( s instanceof Triangle){
		//	boolean flag = (Triangle)s.isATriangle();
		//	if(flag == false){
		//	    ClientGUI.dataTextArea.append("The 3 sides cannot formed a triangle"+ "\n");
		//	}
		//}


        double perimeter = s.getPerimeter();
        double area = s.getArea();

    	ClientGUI.dataTextArea.append("Perimeter = " + perimeter + ", Area = " + area + "\n\n");

      }
}